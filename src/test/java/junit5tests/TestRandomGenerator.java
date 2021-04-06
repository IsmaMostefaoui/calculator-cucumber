package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.Calculator;
import calculator.MyNumber;
import calculator.RandomGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRandomGenerator {

    private final String value = "8";
    Calculator c;
    RandomGenerator op;
    private MyNumber number;
    private MyNumber n;

    @BeforeEach
    public void setUp() {
        c = new Calculator();
    }

    @Test
    public void testConstructor() {
        assertThrows(NumberFormatException.class, () -> op = new RandomGenerator("4s"));
    }

    @Test
    public void testCountDepth() {
        //test whether a randomgenerator has zero depth (i.e. no nested expressions)
        assertEquals(Integer.valueOf(0), c.count(new RandomGenerator(value)).getCountDepth());
    }

    @Test
    public void testCountOps() {
        //test whether a randomgenerator contains zero operations
        assertEquals(Integer.valueOf(0), c.count(new RandomGenerator(value)).getCountOps());
    }

    @Test
    public void testCountNbs() {
        assertEquals(Integer.valueOf(1), c.count(new RandomGenerator(value)).getCountNbs());
    }

    @Test
    public void testRandomization() {
        RandomGenerator rg;
        Dictionary<String, Integer> l = new Hashtable<>();
        int iterationNumber = 1000000;
        int epsilon = iterationNumber / 100 * 20;

        for (int i = 0; i < iterationNumber; i++) {
            rg = new RandomGenerator("2");
            try {
                l.put(c.eval(rg) + "", l.get(c.eval(rg) + "") + 1);
            } catch (Exception exception) {
                l.put(c.eval(rg) + "", 1);
            }
        }
        assertTrue(Math.abs(l.get("0") - l.get("1")) <= epsilon, "0 : " + l.get("0") + "; 1 : " + l.get("1"));
    }
}
