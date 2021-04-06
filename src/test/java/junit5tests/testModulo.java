package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testModulo {
    MyNumber n1, n2, n3, n4, n5;
    List<Expression> e1, e2;
    Plus p1, p2;
    Minus m1, m2;
    Times t1, t2;
    Divides d1;
    Exponents exp1, exp2;
    private Calculator c;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testModuloNumber() {
        try {
            c = new Calculator();
            n1 = new MyNumber("11");
            n2 = new MyNumber("3");
            n3 = new MyNumber("2");

            List<Expression> params = new ArrayList<>();
            Collections.addAll(params, n1, n2);

            assertEquals(c.eval(new Modulo(params)), n3.getValue());

        } catch (Exception e) {
        }
    }

    @Test
    public void testModuloAddition() {
        try {
            c = new Calculator();

            // a+b=c => a mod n+b mod n=c mod n
            n1 = new MyNumber("5");
            n2 = new MyNumber("2");
            n3 = new MyNumber("7");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            p1 = new Plus(e1);
            assertEquals(c.eval(p1), c.eval(n3));

            n1 = new MyNumber("5", "3");
            n2 = new MyNumber("2", "3");
            n3 = new MyNumber("7", "3");
            n4 = new MyNumber("3");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            p1 = new Plus(e1, n4);
            assertEquals(c.eval(p1), c.eval(n3));

            // a=b mod c => a+k=(b+k)mod c
            n1 = new MyNumber("2");
            n2 = new MyNumber("5", "3");
            n3 = new MyNumber("5");
            n4 = new MyNumber("3");
            n5 = new MyNumber("3");

            assertEquals(c.eval(n1), c.eval(n2));

            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n4);
            p1 = new Plus(e1, n5);

            e2 = new ArrayList<>();
            Collections.addAll(e2, n3, n4);
            p2 = new Plus(e2, n5);

            assertEquals(c.eval(p1), c.eval(p2));

            // a=b mod n c=d mod n => a+c=(b+d)mod n
            n1 = new MyNumber("2");
            n2 = new MyNumber("9", "7");
            n3 = new MyNumber("3");
            n4 = new MyNumber("10", "7");
            n5 = new MyNumber("7");

            assertEquals(c.eval(n1), c.eval(n2));
            assertEquals(c.eval(n3), c.eval(n4));

            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n3);
            p1 = new Plus(e1, n5);

            e1 = new ArrayList<>();
            Collections.addAll(e1, n2, n4);
            p2 = new Plus(e1, n5);

            assertEquals(c.eval(p1), c.eval(p2));

            // a=b mod n => -a=-b mod n
            n1 = new MyNumber("4", true);
            n2 = new MyNumber("14", "10", true);
            assertEquals(c.eval(n1), c.eval(n2));

            n1 = new MyNumber("4", false);
            n2 = new MyNumber("14", "10", false);
            assertEquals(c.eval(n1), c.eval(n2));

        } catch (Exception e) {
        }
    }

    @Test
    public void testModuloSubtraction() {
        try {
            c = new Calculator();

            // a+b=c => a mod n+b mod n=c mod n
            n1 = new MyNumber("5");
            n2 = new MyNumber("2");
            n3 = new MyNumber("3");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            m1 = new Minus(e1);
            assertEquals(c.eval(m1), c.eval(n3));

            n1 = new MyNumber("5", "3");
            n2 = new MyNumber("2", "3");
            n3 = new MyNumber("3", "3");
            n4 = new MyNumber("3");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            m1 = new Minus(e1, n4);
            assertEquals(c.eval(m1), c.eval(n3));

            // a=b mod c => a+k=(b+k)mod c
            n1 = new MyNumber("2");
            n2 = new MyNumber("5", "3");
            n3 = new MyNumber("5");
            n4 = new MyNumber("3");
            n5 = new MyNumber("3");

            assertEquals(c.eval(n1), c.eval(n2));

            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n4);
            m1 = new Minus(e1, n5);

            e2 = new ArrayList<>();
            Collections.addAll(e2, n3, n4);
            m2 = new Minus(e2, n5);

            assertEquals(c.eval(m1), c.eval(m2));

            // a=b mod n c=d mod n => a+c=(b+d)mod n
            n1 = new MyNumber("2");
            n2 = new MyNumber("9", "7");
            n3 = new MyNumber("3");
            n4 = new MyNumber("10", "7");
            n5 = new MyNumber("7");

            assertEquals(c.eval(n1), c.eval(n2));
            assertEquals(c.eval(n3), c.eval(n4));

            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n3);
            m1 = new Minus(e1, n5);

            e1 = new ArrayList<>();
            Collections.addAll(e1, n2, n4);
            m2 = new Minus(e1, n5);

            assertEquals(c.eval(m1), c.eval(m2));

        } catch (Exception e) {
        }
    }

    @Test
    public void testModuloMultiplication() {
        try {
            c = new Calculator();

            // a*b=c => a mod n*b mod n=c mod n
            n1 = new MyNumber("5");
            n2 = new MyNumber("2");
            n3 = new MyNumber("10");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            t1 = new Times(e1);
            assertEquals(c.eval(t1), c.eval(n3));

            n1 = new MyNumber("5", "3");
            n2 = new MyNumber("2", "3");
            n3 = new MyNumber("10", "3");
            n4 = new MyNumber("3");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            t1 = new Times(e1, n4);
            assertEquals(c.eval(t1), c.eval(n3));

            // a=b mod c => a*k=b*k mod c
            n1 = new MyNumber("2");
            n2 = new MyNumber("5", "3");
            n3 = new MyNumber("5");
            n4 = new MyNumber("3");
            n5 = new MyNumber("3");

            assertEquals(c.eval(n1), c.eval(n2));

            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n4);
            t1 = new Times(e1, n5);

            e2 = new ArrayList<>();
            Collections.addAll(e2, n3, n4);
            t2 = new Times(e2, n5);

            assertEquals(c.eval(t1), c.eval(t2));

            // a=b mod n c=d mod n => a*c=b*d mod n
            n1 = new MyNumber("2");
            n2 = new MyNumber("9", "7");
            n3 = new MyNumber("3");
            n4 = new MyNumber("10", "7");
            n5 = new MyNumber("7");

            assertEquals(c.eval(n1), c.eval(n2));
            assertEquals(c.eval(n3), c.eval(n4));

            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n3);
            t1 = new Times(e1, n5);

            e1 = new ArrayList<>();
            Collections.addAll(e1, n2, n4);
            t2 = new Times(e1, n5);

            assertEquals(c.eval(t1), c.eval(t2));

        } catch (Exception e) {
        }
    }

    @Test
    public void testModuloDivision() {
        try {
            c = new Calculator();

            // a/b=c => a mod n/b mod n=c mod n
            n1 = new MyNumber("10");
            n2 = new MyNumber("2");
            n3 = new MyNumber("3");
            n4 = new MyNumber("2");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            d1 = new Divides(e1, n3);
            assertEquals(c.eval(d1), c.eval(n4));

        } catch (Exception e) {
        }
    }

    @Test
    public void testModuloExponentiation() {
        try {
            c = new Calculator();

            // a=b mod n => a^k=b^k mod n
            n1 = new MyNumber("2", "3");
            n2 = new MyNumber("5", "3");
            assertEquals(c.eval(n1), c.eval(n2));

            n1 = new MyNumber("2");
            n2 = new MyNumber("5");
            n3 = new MyNumber("3");
            n4 = new MyNumber("3");
            e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n3);
            e2 = new ArrayList<>();
            Collections.addAll(e2, n1, n3);
            exp1 = new Exponents(e1, n4);
            exp2 = new Exponents(e2, n4);
            assertEquals(c.eval(exp1), c.eval(exp2));
        } catch (Exception e) {
        }
    }
}
