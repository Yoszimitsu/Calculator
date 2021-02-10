public class Instruction {
    private InstructionType instructionType;
    private Double number;

    public Instruction() {}

    public Instruction(InstructionType instructionType, Double number) {
        this.instructionType = instructionType;
        this.number = number;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }
}
