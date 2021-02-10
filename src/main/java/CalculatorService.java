import errors.AnalyzeInstructionException;
import errors.EmptyFileException;
import errors.UnknownInstructionException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.Scanner;

public class CalculatorService {

    private final ScriptEngineManager mgr = new ScriptEngineManager();
    private final ScriptEngine engine = mgr.getEngineByName("JavaScript");
    private LinkedList<Instruction> instructionList = new LinkedList<>();
    private boolean withPrecedence;

    public Double calculate(String filePath, boolean withPrecedence) {
        Scanner scanner;
        this.withPrecedence = withPrecedence;

        try {
            scanner = new Scanner(getFile(filePath));

            if (!scanner.hasNextLine()) {
                throw new EmptyFileException("File is empty.");
            }

            while (scanner.hasNextLine()) {
                instructionList.add(analyzeInstruction(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return this.withPrecedence ? executeCalculationsWithPrecedence(instructionList) :
                executeCalculationsWithoutPrecedence(instructionList);
    }

    private Instruction analyzeInstruction(String nextLine) {
        Instruction instruction = new Instruction();

        try {
            if (InstructionType.stream().anyMatch(instructionType -> {
                if (nextLine.toUpperCase().contains(instructionType.toString())) {
                    instruction.setInstructionType(instructionType);
                    return true;
                }
                return false;
            })) {
                var numberString = nextLine.strip().substring(
                        instruction.getInstructionType().toString().length(),
                        nextLine.length());

                instruction.setNumber(Double.valueOf(numberString));
            } else {
                throw new UnknownInstructionException("Not recognized instruction.");
            }
        } catch (Exception e) {
            throw new AnalyzeInstructionException("Error during analyze instructions. " + e.getMessage());
        }
        return instruction;
    }

    private Double executeCalculationsWithoutPrecedence(LinkedList<Instruction> instructionList) {
        StringBuilder equal = new StringBuilder();

        try {
            if (instructionList.getLast().getInstructionType().equals(InstructionType.APPLY)) {
                equal.append(instructionList.getLast().getNumber());

                for (int i = 0; i < instructionList.size() - 1; i++) {
                    equal.append(analyzeOperation(instructionList.get(i).getInstructionType()));
                    equal.append(instructionList.get(i).getNumber());
                    equal.replace(0, equal.toString().length(), String.valueOf(engine.eval(equal.toString())));
                }
            } else {
                throw new AnalyzeInstructionException("\"APPLY\"is not last instruction.");
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        return round(Double.parseDouble(equal.toString()), 4);
    }

    private Double executeCalculationsWithPrecedence(LinkedList<Instruction> instructionList) {
        StringBuilder equal = new StringBuilder();
        Double result = 0.0;

        if (instructionList.getLast().getInstructionType().equals(InstructionType.APPLY)) {
            equal.append(instructionList.getLast().getNumber());

            for (int i = 0; i < instructionList.size() - 1; i++) {
                equal.append(analyzeOperation(instructionList.get(i).getInstructionType()));
                equal.append(instructionList.get(i).getNumber());
            }
        } else {
            throw new AnalyzeInstructionException("\"APPLY\"is not last instruction.");
        }
        try {
            result = (Double) engine.eval(equal.toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return round(result, 4);
    }

    private String analyzeOperation(InstructionType instructionType) {
        if (instructionType.equals(InstructionType.ADD)) {
            return "+";
        }
        if (instructionType.equals(InstructionType.SUBTRACT)) {
            return "-";
        }
        if (instructionType.equals(InstructionType.MULTIPLY)) {
            return "*";
        }
        if (instructionType.equals(InstructionType.DIVIDE)) {
            return "/";
        }
        return "";
    }

    private File getFile(String filePath) {
        return new File(filePath);
    }

    private static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
