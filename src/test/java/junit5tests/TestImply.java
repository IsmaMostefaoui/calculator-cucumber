package junit5tests;

import calculator.*;
import calculator.error.IllegalConstruction;
import calculator.expression.Expression;
import calculator.expression.MyNumber;
import calculator.expression.operation.Imply;
import calculator.expression.operation.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestImply {

    private final String value1 = "1";
    private final String value2 = "1";
    private Imply op;
    private List<Expression> params;
    private Calculator c;

    @BeforeEach
    public void setUp() {
        c = new Calculator();
        params = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            op = new Imply(params);
            op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Imply(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testConstructor2() {
        // A Times expression should not be the same as a Imply expression
        try {
            assertNotEquals(op, new Or(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            Imply d = new Imply(p, Notation.INFIX);
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
            Imply e = new Imply(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Imply(params));
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
        String prefix = "=> (" + value1 + ", " + value2 + ")";
        assertEquals(prefix, c.convertToString(op, Notation.PREFIX));
    }

    @Test
    public void testInfix() {
        String infix = "( " + value1 + " => " + value2 + " )";
        assertEquals(infix, c.convertToString(op, Notation.INFIX));
    }

    @Test
    public void testPostfix() {
        String postfix = "(" + value1 + ", " + value2 + ") =>";
        assertEquals(postfix, c.convertToString(op, Notation.POSTFIX));
    }

}
