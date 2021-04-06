package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDivides {

    private final String value1 = "8";
    private final String value2 = "6";
    private Divides op;
    private List<Expression> params;
    private Calculator c;

    @BeforeEach
    public void setUp() {
        c = new Calculator();
        params = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            op = new Divides(params);
            op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
    }

    @Test
    public void divisionZero() {
        //It should handle division by zero
        try {
            List<Expression> col = new ArrayList<>();
            Collections.addAll(col, new MyNumber("8"), new MyNumber("0"));
            Divides de = new Divides(col);
            assertThrows(DivisionByZero.class, () -> de.op(new BigInteger("10"), new BigInteger("0")));
        } catch (IllegalConstruction e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testConstructor2() {
        // A Times expression should not be the same as a Divides expression
        try {
            assertNotEquals(op, new Times(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            Divides d = new Divides(p, Notation.INFIX);
            assertEquals(op, d);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testEquals2() {
        assertDoesNotThrow(() -> op.equals(null)); // Direct way to to test if the null case is handled.
    }

    @Test
    public void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            Divides e = new Divides(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Divides(params));
    }

    @Test
    public void testCountDepth() {
        assertEquals(Integer.valueOf(1), c.count(op).getCountDepth());
    }

    @Test
    public void testCountOps() {
        assertEquals(Integer.valueOf(1), c.count(op).getCountOps());
    }

    @Test
    public void testCountNbs() {
        assertEquals(Integer.valueOf(2), c.count(op).getCountNbs());
    }

    @Test
    public void testPrefix() {
        String prefix = "/ (" + value1 + "_{10}, " + value2 + "_{10})";
        assertEquals(prefix, c.convertToString(op, Notation.PREFIX));
    }

    @Test
    public void testInfix() {
        String infix = "( " + value1 + "_{10} / " + value2 + "_{10} )";
        assertEquals(infix, c.convertToString(op, Notation.INFIX));
    }

    @Test
    public void testPostfix() {
        String postfix = "(" + value1 + "_{10}, " + value2 + "_{10}) /";
        assertEquals(postfix, c.convertToString(op, Notation.POSTFIX));
    }

}
