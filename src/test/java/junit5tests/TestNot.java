package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestNot {

    private final String value1 = "1";
    private final String value2 = "1";
    private Not op;
    private List<Expression> params;
    private Calculator c;

    @BeforeEach
    public void setUp() {
        c = new Calculator();
        params = new ArrayList<>(Arrays.asList(new MyNumber(value1)));
        try {
            op = new Not(params);
            op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Not(null));
    }


    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testConstructor2() {
        // A Times expression should not be the same as a Not expression
        try {
            assertNotEquals(op, new Times(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }


    @Test
    public void testConstructor3() {
        // It should not be possible to create an expression with more than 1 number for not unary operation
        assertThrows(IllegalConstruction.class, () -> op = new Not(Arrays.asList(new MyNumber(value1), new MyNumber(value1))));
    }

    @Test
    public void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1)));
        try {
            Not d = new Not(p, Notation.INFIX);
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
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1)));
        try {
            Not e = new Not(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Not(params));
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
        assertEquals(Integer.valueOf(1), c.count(op).getCountNbs());
    }

    @Test
    public void testPrefix() {
        String prefix = "! (" + value1 + ")";
        assertEquals(prefix, c.convertToString(op, Notation.PREFIX));
    }

    @Test
    public void testInfix() {
        String infix = "! (" + value1 + ")";
        assertEquals(infix, c.convertToString(op, Notation.INFIX));
    }

    @Test
    public void testPostfix() {
        String postfix = "! (" + value1 + ")";
        assertEquals(postfix, c.convertToString(op, Notation.POSTFIX));
    }

}
