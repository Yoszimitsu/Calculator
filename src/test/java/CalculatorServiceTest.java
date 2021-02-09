import errors.EmptyFileException;
import errors.UnknownInstructionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

class CalculatorServiceTest {

    @Mock
    CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void emptyTest() {
        assertTrue(true);
    }

    @Test
    void executeCalculationTest_file1() {
        when(calculatorService.getFile(anyString())).thenReturn((getFile("/src/test/java/resources/test-file-1.txt")));

        var result = calculatorService.calculate();

        assertEquals(36, result);
    }

    @Test
    void executeCalculationTest_file2() {
        when(calculatorService.getFile(anyString())).thenReturn((getFile("/src/test/java/resources/test-file-2.txt")));

        var result = calculatorService.calculate();

        assertEquals(32, result);
    }

    @Test
    void executeCalculationTest_file3() {
        when(calculatorService.getFile(anyString())).thenReturn((getFile("/src/test/java/resources/test-file-3.txt")));

        var result = calculatorService.calculate();

        assertEquals(1, result);
    }


    @Test
    void executeCalculationTest_emptyFileException() {
        when(calculatorService.getFile(anyString())).thenReturn((getFile("/src/test/java/resources/test-empty-file.txt")));
        assertThrows(EmptyFileException.class,
                () -> {
                    calculatorService.calculate();
                }
        );
    }

    @Test
    void executeCalculationTest_unknownInstructionException() {
        when(calculatorService.getFile(anyString())).thenReturn((getFile("/src/test/java/resources/unknown-instruction-file.txt")));
        assertThrows(UnknownInstructionException.class,
                () -> {
                    calculatorService.calculate();
                }
        );
    }


    private File getFile(String filePath) {
        return new File(filePath);
    }

}
