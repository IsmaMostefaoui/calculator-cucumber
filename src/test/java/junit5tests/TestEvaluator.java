package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;
import visitor.Evaluator;

import java.util.Arrays;

public class TestEvaluator {

    @SuppressWarnings("unused")
    private Evaluator visitor;
    private Calculator calc;
    private int value1, value2;
    private Expression op;

    @BeforeEach
    public void setUp() {
        visitor = new Evaluator();
        calc = new Calculator();
        value1 = 8;
        value2 = 6;
    }

    @Test
    public void testEvaluatorMyNumber() {
        assertEquals( value1,
                      calc.eval(new MyNumber(value1)));
    }

    @Test
    public void testEvaluatorDivides() {
        try { op = new Divides(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
          assertEquals( value1 / value2,
                        calc.eval(op) );
          }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorPlus() {
        try { op = new Plus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals( value1 + value2,
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorMinus() {
        try { op = new Minus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals( value1 - value2,
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorTimes() {
        try { op = new Times(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals( value1 * value2,
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorAnd() {
        value1 = 1;
        value2 = 0;
        try{
            op = new And(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals((value1*value2)==0?0:1,calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorOr() {
        try{
            op = new Or(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals((value1+value2)==0?0:1,calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorNot() {
        try{
            op = new Not(Arrays.asList(new MyNumber(value1)));
            assertEquals((value1)==0?1:0,calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorImply() {
        int notL = (value1 != 0)? 0 : 1;
        int expected = (notL+value2) != 0 ? 1 : 0;
        try{
            op = new Imply(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(expected,calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    public void testEvaluatorEquivalence() {
        int notL = (value1 != 0)? 0 : 1;
        int imply1 = (notL+value2) != 0 ? 1 : 0;
        int notR = (value2 !=0) ? 0 : 1;
        int imply2 = (notR+value1) != 0 ? 1 : 0;
        int expected = (imply1*imply2) != 0 ? 1 : 0;
        try{
            op = new Equivalence(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals(expected,calc.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }
}
