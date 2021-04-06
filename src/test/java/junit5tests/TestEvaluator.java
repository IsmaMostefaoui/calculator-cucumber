package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;

import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(new BigInteger(value1), calc.eval(new MyNumber(value1)).asNumber());


    }


    @Test
    public void testEvaluatorMyDateTime() {
        ZonedDateTime zdt = ZonedDateTime.now();
        MyDateTime mdt = new MyDateTime(zdt);
        assertEquals(zdt, calc.eval(mdt).asZonedDateTime());

    }

    @Test
    public void testEvaluatorMyTimeDuration() {
        Duration d = Duration.ofDays(2);
        MyTimeDuration mtd = new MyTimeDuration(d);
        assertEquals(d, calc.eval(mtd).asDuration());

    }

    @Test
    public void testEvaluatorDivides() {
        try {
            op = new Divides(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));

            assertEquals(new BigInteger(value1).divide(new BigInteger(value2)),
                    calc.eval(op).asNumber());


            ZonedDateTime zdt = ZonedDateTime.now();
            ZonedDateTime zdt1 = ZonedDateTime.now().plusHours(5);
            op = new Divides(Arrays.asList(new MyDateTime(zdt), new MyTimeDuration(Duration.ofHours(2))));
            assertTrue(calc.eval(op).isUndefined());

            op = new Divides(Arrays.asList(new MyDateTime(zdt), new MyDateTime(zdt1)));
            assertTrue(calc.eval(op).isUndefined());


        } catch (IllegalConstruction e) {

            fail();
        }


    }

    @Test
    public void testEvaluatorPlus() {
        try {
            op = new Plus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));

            assertEquals(new BigInteger(value1).add(new BigInteger(value2)),
                    calc.eval(op).asNumber());


            ZonedDateTime zdt = ZonedDateTime.now();
            ZonedDateTime zdt1 = ZonedDateTime.now().plusHours(5);
            op = new Plus(Arrays.asList(new MyDateTime(zdt), new MyDateTime(zdt1)));
            assertTrue(calc.eval(op).isUndefined());

            Duration d = Duration.ofHours(2);
            op = new Plus(Arrays.asList(new MyDateTime(zdt), new MyTimeDuration(d)));
            assertEquals(zdt.plus(d), calc.eval(op).asZonedDateTime());
        } catch (IllegalConstruction e) {

            fail();
        }
    }

    @Test
    public void testEvaluatorMinus() {
        try {
            op = new Minus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));

            assertEquals(new BigInteger(value1).subtract(new BigInteger(value2)),
                    calc.eval(op).asNumber());


            ZonedDateTime zdt = ZonedDateTime.now();
            ZonedDateTime zdt1 = ZonedDateTime.now();
            op = new Minus(Arrays.asList(new MyDateTime(zdt), new MyDateTime(zdt1)));
            assertEquals(Duration.between(zdt, zdt1), calc.eval(op).asDuration());

            Duration d = Duration.ofHours(2);
            op = new Minus(Arrays.asList(new MyDateTime(zdt), new MyTimeDuration(d)));
            assertEquals(zdt.minus(d), calc.eval(op).asZonedDateTime());


            op = new Minus(Arrays.asList(new MyTimeDuration(d), new MyDateTime(zdt)));
            assertTrue(calc.eval(op).isUndefined());

        } catch (IllegalConstruction e) {

            fail();
        }
    }

    @Test
    public void testEvaluatorTimes() {

        try {
            op = new Times(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(new BigInteger(value1).multiply(new BigInteger(value2)),
                    calc.eval(op).asNumber());
            ZonedDateTime zdt = ZonedDateTime.now();
            ZonedDateTime zdt1 = ZonedDateTime.now().plusHours(5);
            op = new Times(Arrays.asList(new MyDateTime(zdt), new MyTimeDuration(Duration.ofHours(2))));
            assertTrue(calc.eval(op).isUndefined());

            op = new Times(Arrays.asList(new MyDateTime(zdt), new MyDateTime(zdt1)));
            assertTrue(calc.eval(op).isUndefined());
        } catch (IllegalConstruction e) {

            fail();
        }
    }

    @Test
    public void testEvaluatorAnd() {
        value1 = "1";
        value2 = "0";
        try {
            op = new And(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(BigInteger.ZERO, calc.eval(op).asNumber());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorOr() {
        try {
            op = new Or(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(BigInteger.ONE, calc.eval(op).asNumber());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorNot() {
        try {
            op = new Not(Arrays.asList(new MyNumber(value1)));
            assertEquals(BigInteger.ZERO, calc.eval(op).asNumber());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorImply() {

        try {
            op = new Imply(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(BigInteger.ONE, calc.eval(op).asNumber());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorEquivalence() {

        try {
            op = new Equivalence(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(BigInteger.ONE, calc.eval(op).asNumber());
        } catch (IllegalConstruction e) {
            fail();
        }
    }
}
