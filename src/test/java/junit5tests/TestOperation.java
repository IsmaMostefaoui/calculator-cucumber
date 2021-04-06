package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOperation {

    private Operation o;
    private Operation o2;
    private Calculator c;

    @BeforeEach
    public void setUp() throws Exception {
        c = new Calculator();
        List<Expression> params1 =
                new ArrayList<>(Arrays.asList(new MyNumber("3"), new MyNumber("4"), new MyNumber("5")));
        List<Expression> params2 =
                new ArrayList<>(Arrays.asList(new MyNumber("5"), new MyNumber("4")));
        List<Expression> params3 =
                new ArrayList<>(Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber("7")));
        o = new Divides(params3);
        o2 = new Divides(params3);
    }

    @Test
    public void testEquals() {
        assertEquals(o, o2);
    }

    @Test
    public void testCountDepth() {
        assertEquals(Integer.valueOf(2), c.count(o).getCountDepth());
    }

    @Test
    public void testCountOps() {
        assertEquals(Integer.valueOf(3), c.count(o).getCountOps());
    }

    @Test
    public void testCountNbs() {
        assertEquals(Integer.valueOf(6), c.count(o).getCountNbs());
    }

}
