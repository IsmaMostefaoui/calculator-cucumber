package calculator;

import java.util.ArrayList;
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

            MyNumber n1 = new MyNumber("4");
            MyNumber n2 = new MyNumber("7");
            MyNumber n3 = new MyNumber("3");
            MyNumber n4 = new MyNumber("2");
            List<Expression> e1 = new ArrayList<>();
            Collections.addAll(e1, n1, n2);
            Converter d1 = new Converter(n1, 2);
            c.print(d1);

            /*MyNumber number1=new MyNumber("z",37);
            Converter number2=new Converter(new MyNumber("3"), 2);
            c.print(number1);
            c.print(number2, 2);
            List<Expression> params=new ArrayList<>();
            Collections.addAll(params,number1,number2);
            c.print(new Plus(params), 2);*/
            // Here is an example of how to use the calculator:

            /*e = new MyNumber("8");
            // c.print(e);
            c.printExpressionDetails(e, Notation.INFIX);
            c.eval(e);

            List<Expression> params = new ArrayList<>();
            Collections.addAll(params, new MyNumber("3"), new MyNumber("4"), new MyNumber("5"));
            e = new Plus(params);
            c.printExpressionDetails(e, Notation.PREFIX);
            c.eval(e);

            List<Expression> params2 = new ArrayList<>();
            Collections.addAll(params2, new MyNumber("5"), new MyNumber("3"));
            e = new Minus(params2);
            c.printExpressionDetails(e, Notation.INFIX);
            c.eval(e);

            List<Expression> params3 = new ArrayList<>();
            Collections.addAll(params3, new Plus(params), new Minus(params2));
            e = new Times(params3);
            c.printExpressionDetails(e);
            c.eval(e);

            List<Expression> params4 = new ArrayList<>();
            Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber("0"));
            e = new Divides(params4);
            c.printExpressionDetails(e, Notation.POSTFIX);
            c.eval(e);

            List<Expression> params5 = new ArrayList<>();
            Collections.addAll(params5, new Plus(params), new Minus(params2), new Times(params2), new MyNumber("1"));
            e = new Divides(params5);
            c.printExpressionDetails(e, Notation.POSTFIX);
            c.eval(e);*/
        } catch (NumberFormatException exception) {

        } catch (InnapropriateBase exception) {
        }
    }

}
