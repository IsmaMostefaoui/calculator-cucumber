package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMin {

    Calculator c;
    Min op;

    @Test
    public void testConstructor() {
        assertThrows(IllegalConstruction.class, () -> op = new Min(null));
    }

    @Test
    public void testCountDepth() {
        try {
            c = new Calculator();
            op = new Min(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(1), c.count(op).getCountDepth());
        } catch (IllegalConstruction exception) {
        }
    }

    @Test
    public void testCountOps() {
        try {
            c = new Calculator();
            op = new Min(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(1), c.count(op).getCountOps());
        } catch (IllegalConstruction exception) {
        }
    }

    @Test
    public void testCountNbs() {
        try {
            c = new Calculator();
            op = new Min(new ArrayList<>(Arrays.asList(new MyNumber("4"), new MyNumber("7"))));
            assertEquals(Integer.valueOf(2), c.count(op).getCountNbs());
        } catch (IllegalConstruction exception) {
        }
    }

    @Test
    public void testMin() {
        try {
            c = new Calculator();
            MyNumber mn1 = new MyNumber("1");
            MyNumber mn2 = new MyNumber("2");
            List<Expression> e = new ArrayList<>();
            Collections.addAll(e, mn1, mn2);
            Min m = new Min(e);
            assertEquals(c.eval(m), mn1.getValue());
        } catch (IllegalConstruction exception) {
        }
    }
}
