package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***************************************
 * A very simple Calculator in Java    *
 * Tom Mens, February 2021             *
 * University of Mons - UMONS          *
 * Département d'Informatique          *
 * Faculté des Sciences                *
 ***************************************/

public class Main2 {

    public static void main(String[] args) {

        Expression e;
        Calculator c = new Calculator();

        try {
            // Here is an example of how to use the calculator:

            e = new MyNumber(8);
            // c.print(e);

            List<Expression> params = new ArrayList<>();
            Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
            e = new Plus(params);
            c.eval(e);

            List<Expression> params2 = new ArrayList<>();
            Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
            e = new Minus(params2);
            c.eval(e);

            c.printLog(Notation.INFIX);

            c.loadHistory();
            c.printLog(Notation.INFIX);
        } catch (IllegalConstruction exception) {
            System.out.println("cannot create operations without parameters");
        }
    }

}
