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

public class Main {

    public static void main(String[] args) {

        Expression e;
        Calculator c = new Calculator();

        try {
            // Here is an example of how to use the calculator:

            e = new MyNumber(8);
            // c.print(e);
            c.printExpressionDetails(e, Notation.INFIX);

            List<Expression> params = new ArrayList<>();
            Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
            e = new Plus(params);
            c.printExpressionDetails(e, Notation.PREFIX);
            c.eval(e);

            List<Expression> params2 = new ArrayList<>();
            Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
            e = new Minus(params2);
            c.printExpressionDetails(e, Notation.INFIX);
            c.eval(e);

            List<Expression> params3 = new ArrayList<>();
            Collections.addAll(params3, new Plus(params), new Minus(params2));
            e = new Times(params3);
            c.printExpressionDetails(e);
            c.eval(e);

            List<Expression> params4 = new ArrayList<>();
            Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(0));
            e = new Divides(params4);
            c.printExpressionDetails(e, Notation.POSTFIX);
            c.eval(e);

            List<Expression> params5 = new ArrayList<>();
            Collections.addAll(params5, new Plus(params), new Minus(params2), new Times(params2), new MyNumber(1));
            e = new Divides(params5);
            c.printExpressionDetails(e, Notation.POSTFIX);
            c.eval(e);

            List<Expression> params6 = new ArrayList<>();
            Collections.addAll(params6, new Not(Arrays.asList(new MyNumber(1))), new Or(params2), new And(params2), new MyNumber(1));
            e = new Or(params6);
            c.printExpressionDetails(e, Notation.INFIX);
            c.eval(e);
            System.out.println("-----------\nHISTORY");
            System.out.println("Undo done -> ");
            e = c.undo();
            c.print(e);

            System.out.println("Redo done -> ");
            e = c.redo();
            c.print(e);

            System.out.println("Undo done -> ");
            c.undo();

            System.out.println("Undo done -> ");
            e = c.undo();
            c.print(e);

            c.printLog(Notation.INFIX);

            c.saveHistory();
        } catch (IllegalConstruction exception) {
            System.out.println("cannot create operations without parameters");
        }
    }

}
