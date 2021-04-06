package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestModularInverse {

    Calculator c;
    ModularInverse op;

    @Before
    public void setUp() {
        c = new Calculator();
    }

    @Test
    public void testConstructor() {
        assertThrows(IllegalConstruction.class, () -> op = new ModularInverse(null));
    }

    @Test
    public void testCountDepth() {
        try {
            c = new Calculator();
            op = new ModularInverse(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(1), c.count(op).getCountDepth());
        } catch (IllegalConstruction exception) {
        }
    }

    @Test
    public void testCountOps() {
        try {
            c = new Calculator();
            op = new ModularInverse(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(1), c.count(op).getCountOps());
        } catch (IllegalConstruction exception) {
        }
    }

    @Test
    public void testCountNbs() {
        try {
            c = new Calculator();
            op = new ModularInverse(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(2), c.count(op).getCountNbs());
        } catch (IllegalConstruction exception) {
        }
    }

    @Test
    public void testNotInversibleInteger() {
        try {
            op = new ModularInverse(new ArrayList<Expression>(Arrays.asList(new MyNumber("10"), new MyNumber("2"))));
            assertThrows(ArithmeticException.class, () -> op.op(new BigInteger("10"), new BigInteger("2")));
        } catch (IllegalConstruction exception) {
        }
    }
}
