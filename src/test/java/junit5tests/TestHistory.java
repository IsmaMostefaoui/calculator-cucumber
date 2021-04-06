package junit5tests;

import calculator.Calculator;
import calculator.Expression;
import calculator.MyNumber;
import calculator.NoActionLeftInHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestHistory {

    private Evaluator visitor;
    private Calculator calc;
    private String value1, value2;
    private Expression op1, op2;

    @BeforeEach
    public void setup() {
        visitor = new Evaluator();
        calc = new Calculator();
        value1 = "1";
        value2 = "1";

        op1 = new MyNumber(value1);
        op2 = new MyNumber(value2);
    }

    @Test
    public void testExecute() {
        calc.eval(op1);
        calc.eval(op2);
        assertEquals(op2, calc.getCurrent());
    }

    @Test
    public void testUndo() {
        calc.eval(op1);
        calc.eval(op2);
        calc.undo();
        assertEquals(op1, calc.getCurrent());
    }

    @Test
    public void testRedo() {
        calc.eval(op1);
        calc.eval(op2);
        calc.undo();
        calc.redo();
        assertEquals(op2, calc.getCurrent());
    }

    @Test
    public void testNoActionLeft1() {
        calc.eval(op1);

        assertThrows(NoActionLeftInHistory.class, () -> calc.redo());
    }

    @Test
    public void testNoActionLeft2() {
        calc.eval(op1);
        assertThrows(NoActionLeftInHistory.class, () -> calc.undo());
    }

    @Test
    public void testClearHistory() {
        calc.eval(op1);
        calc.eval(op2);
        calc.clearHistory();
        assert (calc.getActionHistory().isEmpty());
    }

    @Test
    public void testSaveLoad() {
        calc.eval(op1);
        calc.eval(op2);
        List<Expression> precHistory = calc.getActionHistory();
        calc.saveHistory();
        calc.clearHistory();
        calc.loadHistory();
        assertEquals(precHistory, calc.getActionHistory());
    }

}
