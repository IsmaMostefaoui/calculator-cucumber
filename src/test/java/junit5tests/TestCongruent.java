package junit5tests;

import calculator.Calculator;
import calculator.CalculatorResult;
import calculator.error.IllegalConstruction;
import calculator.expression.Expression;
import calculator.expression.MyNumber;
import calculator.expression.operation.Congruent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestCongruent {

    private final String value1 = "11";
    private final String value2 = "3";
    private final String value3 = "4";
    private final MyNumber modulo=new MyNumber("8");
    Congruent congruent;
    Calculator c=new Calculator();
    Congruent op;

    @Test
    public void testConstructor() {
        assertThrows(IllegalConstruction.class, () -> op = new Congruent( null, new MyNumber(value1)));
    }

    @Test
    public void testCongruentTrue(){
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            congruent = new Congruent(p,modulo);
            assertEquals(CalculatorResult.TRUE,c.eval(congruent));
        } catch (IllegalConstruction illegalConstruction) {
            fail();
        }
    }

    @Test
    public void testCongruentFalse(){
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value3)));
        try {
            congruent = new Congruent(p,modulo);
            assertEquals(CalculatorResult.FALSE,c.eval(congruent));
        } catch (IllegalConstruction illegalConstruction) {
            fail();
        }
    }
}
