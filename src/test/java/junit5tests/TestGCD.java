package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import calculator.error.IllegalConstruction;
import calculator.expression.Expression;
import calculator.expression.MyNumber;
import calculator.expression.operation.GCD;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGCD {

    private final String value1 = "8";
    private final String value2 = "12";
    private final String value3 = "4";
    private List<Expression> params;
    private Calculator c;
    private GCD op;

    @Before
    public void setUp() {
        c = new Calculator();
    }

    @Test
    public void testConstructor() {
        assertThrows(IllegalConstruction.class, () -> op = new GCD(null));
    }

    @Test
    public void testCountDepth() {
        try {
            c = new Calculator();
            op = new GCD(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(1), c.count(op).getCountDepth());
        } catch (IllegalConstruction exception) {
            fail();
        }
    }

    @Test
    public void testCountOps() {
        try {
            c = new Calculator();
            op = new GCD(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(1), c.count(op).getCountOps());
        } catch (IllegalConstruction exception) {
            fail();
        }
    }

    @Test
    public void testCountNbs() {
        try {
            c = new Calculator();
            op = new GCD(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(2), c.count(op).getCountNbs());
        } catch (IllegalConstruction exception) {
            fail();
        }
    }

    @Test
    public void testGCD() {
        try {
            MyNumber mn1 = new MyNumber(value1);
            MyNumber mn2 = new MyNumber(value2);
            MyNumber mn3 = new MyNumber(value3);
            params = new ArrayList<>();
            Collections.addAll(params, mn1, mn2);
            GCD gcd = new GCD(params);
            assertEquals(c.eval(gcd), c.eval(mn3));
        } catch (Exception e) {
        }
    }
}
