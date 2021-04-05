package junit5tests;

import calculator.CalculatorResult;
import calculator.IllegalConstruction;
import calculator.MyDateTime;
import calculator.MyTimeDuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class TestCalculationResult {

    @Test
    public void testConstruct() {
        CalculatorResult cr = new CalculatorResult(Duration.ofHours(5));
        assertFalse(cr.isUndefined());
        assertEquals(cr.getType(), CalculatorResult.ResultType.DURATION);

        cr = new CalculatorResult(ZonedDateTime.now());
        assertFalse(cr.isUndefined());
        assertEquals(cr.getType(), CalculatorResult.ResultType.DATETIME);

        cr = new CalculatorResult(5.15);
        assertFalse(cr.isUndefined());
        assertEquals(cr.getType(), CalculatorResult.ResultType.NUMBER);

        cr = CalculatorResult.UNDEFINED;
        assertTrue(cr.isUndefined());
        assertEquals(cr.getType(), CalculatorResult.ResultType.UNDEFINED);

    }
}
