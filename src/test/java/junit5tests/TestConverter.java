package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.Calculator;
import calculator.Converter;
import calculator.InnapropriateBase;
import calculator.MyNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

public class TestConverter {

    private final String value1 = "11";
    private final String value2 = "3";
    private final int radix = 2;
    Calculator c;
    Converter op, op2;

    @BeforeEach
    public void setUp() {
        c = new Calculator();
        try {
            op2 = new Converter(new MyNumber("4"), 2);
        } catch (InnapropriateBase exception) {
        }
    }

    @Test
    public void testConstructor() {
        assertThrows(NullPointerException.class, () -> op = new Converter((BigInteger) null, 2));
    }

    @Test
    public void testConstructor2() {
        assertThrows(InnapropriateBase.class, () -> op = new Converter(new MyNumber("4"), 0));
    }

    @Test
    public void testConstructor3() {
        assertThrows(InnapropriateBase.class, () -> op = new Converter(new MyNumber("4"), 37));
    }

    @Test
    public void testCountDepth() {
        assertEquals(Integer.valueOf(0), c.count(op2).getCountDepth());
    }

    @Test
    public void testCountOps() {
        assertEquals(Integer.valueOf(0), c.count(op2).getCountOps());
    }

    @Test
    public void testCountNbs() {
        assertEquals(Integer.valueOf(1), c.count(op2).getCountNbs());
    }

    @Test
    public void testNumberRadix() {
        try {
            MyNumber number1 = new MyNumber(value1, radix);
            Converter number2 = new Converter(new MyNumber(value2), radix);
            assertEquals(c.eval(number1), c.eval(number2));
        } catch (Exception e) {
            fail();
        }
    }
}
