package calculator;

import visitor.Countator;
import visitor.Evaluator;
import visitor.Stringator;

import java.math.BigInteger;

public class Calculator {

    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    public void print(Expression e, Notation notation) {
        System.out.println("\n\nThe result of evaluating expression " + convertToString(e, notation));
        System.out.println("is: " + eval(e) + ".");
    }

    public void print(Expression e, Notation notation, int radix) {
        System.out.println("\n\nThe result of evaluating expression " + convertToString(e, notation));
        System.out.println("is: " + eval(e).toString(radix) + ".");
    }

    public void print(Expression e) {
        print(e, Notation.INFIX);
    }

    public void print(Expression e, int radix) {
        print(e, Notation.INFIX, radix);
    }

    public void printExpressionDetails(Expression e, Notation notation) {
        print(e, notation);
        Countator c = count(e);
        System.out.print("It contains " + c.getCountDepth() + " levels of nested expressions, ");
        System.out.print(c.getCountOps() + " operations");
        System.out.println(" and " + c.getCountNbs() + " numbers.");
    }

    public void printExpressionDetails(Expression e) {
        printExpressionDetails(e, Notation.INFIX);
    }

    public BigInteger eval(Expression e) {
        // create a new visitor to evaluate expressions
        Evaluator v = new Evaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        try {
            e.accept(v);
        } catch (DivisionByZero d) {
            System.err.println("\nDivision by Zero in : \"" + this.convertToString(e, Notation.INFIX) + "\"");
        } catch (ArithmeticException a) {
            System.err.println("\nNot invertible integer : \"" + this.convertToString(e, Notation.INFIX) + "\"");
        }
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    public String convertToString(Expression e, Notation notation) {
        Stringator s = new Stringator();
        return s.getString(e, notation);
    }

    public Countator count(Expression e) {
        Countator c = new Countator();
        e.accept(c);
        return c;
    }

    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
