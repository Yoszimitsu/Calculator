import errors.AnalyzeInstructionException;
import errors.EmptyFileException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorServiceTest {

    CalculatorService calculatorService = new CalculatorService();

    @Test
    public void executeCalculationTest_file1_withoutPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-1.txt", false);
        assertEquals(36, result, 0);
    }

    @Test
    public void executeCalculationTest_file1_withPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-1.txt", true);
        assertEquals(16, result, 0);
    }

    @Test    public void executeCalculationTest_file2_withoutPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-2.txt", false);
        assertEquals(16, result, 0);
    }

    @Test
    public void executeCalculationTest_file2_withPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-2.txt", true);
        assertEquals(31, result, 0);
    }

    @Test
    public void executeCalculationTest_file3_withoutPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-3.txt", false);
        assertEquals(1, result, 0);
    }

    @Test
    public void executeCalculationTest_file3_withPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-3.txt", true);
        assertEquals(1, result, 0);
    }

    @Test
    public void executeCalculationTest_file4_withoutPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-4.txt", false);
        assertEquals(16.6667, result, 0);
    }

    @Test
    public void executeCalculationTest_file4_withPrecedence() {
        var result = calculatorService.calculate("./src/test/java/resources/test-file-4.txt", true);
        assertEquals(36.1111, result, 0);
    }


    @Test
    public void executeCalculationTest_emptyFileException() {
        Assert.assertThrows(EmptyFileException.class,
                () -> calculatorService.calculate("./src/test/java/resources/test-empty-file.txt", false)
        );
    }

    @Test
    public void executeCalculationTest_analyzeInstructionException() {
        Assert.assertThrows(AnalyzeInstructionException.class,
                () -> calculatorService.calculate("./src/test/java/resources/test-wrong-instruction-structure-file.txt", false)
        );
    }

    @Test
    public void executeCalculationTest_unknownInstructionException() {
        Assert.assertThrows(AnalyzeInstructionException.class,
                () -> calculatorService.calculate("./src/test/java/resources/test-unknown-instruction-file.txt", false)
        );
    }
}
