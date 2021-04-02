package junit5tests;

import calculator.*;

import org.junit.Before;
import visitor.Evaluator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestHistory {

    private Evaluator visitor;
    private Calculator calc;
    private int value1, value2;
    private Expression op1, op2;

    @BeforeEach
    public void setup(){
        visitor = new Evaluator();
        calc = new Calculator();
        value1 = 1;
        value2 = 2;

        op1 = new MyNumber(value1);
        op2 = new MyNumber(value2);
    }

    @Test
    public void testExecute(){
        calc.eval(op1);
        calc.eval(op2);
        assertEquals(op2, calc.getCurrent());
    }

    @Test
    public void testUndo(){
        calc.eval(op1);
        calc.eval(op2);
        calc.undo();
        assertEquals(op1, calc.getCurrent());
    }

    @Test
    public void testRedo(){
        calc.eval(op1);
        calc.eval(op2);
        calc.undo();
        calc.redo();
        assertEquals(op2, calc.getCurrent());
    }

    @Test
    public void testNoActionLeft1(){
        calc.eval(op1);

        assertThrows(NoActionLeftInHistory.class, () -> calc.redo());
    }

    @Test
    public void testNoActionLeft2(){
        calc.eval(op1);
        assertThrows(NoActionLeftInHistory.class, () -> calc.undo());
    }

    @Test
    public void testClearHistory(){
        calc.eval(op1);
        calc.eval(op2);
        calc.clearHistory();
        assert(calc.getActionHistory().isEmpty());
    }

    @Test
    public void testSaveLoad(){
        calc.eval(op1);
        calc.eval(op2);
        List<Expression> precHistory = calc.getActionHistory();
        calc.saveHistory();
        calc.clearHistory();
        calc.loadHistory();
        assertEquals(precHistory, calc.getActionHistory());
    }

}
