package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestEvaluator {

    @SuppressWarnings("unused")
    private Evaluator visitor;
    private Calculator calc;
    private String value1, value2;
    private Expression op;

    @BeforeEach
    public void setUp() {
        visitor = new Evaluator();
        calc = new Calculator();
        value1 = "8";
        value2 = "6";
    }

    @Test
    public void testEvaluatorMyNumber() {
        assertEquals(new BigInteger(value1), calc.eval(new MyNumber(value1)));
    }

    @Test
    public void testEvaluatorDivides() {
        try {
            op = new Divides(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(new BigInteger(value1).divide(new BigInteger(value2)),
                    calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorPlus() {
        try {
            op = new Plus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(new BigInteger(value1).add(new BigInteger(value2)),
                    calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorMinus() {
        try {
            op = new Minus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(new BigInteger(value1).subtract(new BigInteger(value2)),
                    calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorTimes() {
        try {
            op = new Times(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(new BigInteger(value1).multiply(new BigInteger(value2)),
                    calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

}
