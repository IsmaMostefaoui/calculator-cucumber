package calculator;

import calculator.error.CalculatorMemoryError;
import calculator.error.NoActionLeftInHistory;
import calculator.expression.Expression;
import visitor.Countator;
import visitor.Evaluator;
import visitor.Stringator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    private Expression[] memory;
    private CommandHandler ch;
    private List<Expression> actionHistory;

    private UnitsConverter converter = new UnitsConverter();
    public Calculator(int memorySize) {
        this.memory = new Expression[memorySize];
        this.ch = new CommandHandler();
        this.actionHistory = new ArrayList<>();
    }

    public Calculator() {
        this(3);
    }

    public void print(Expression e, Notation notation) {
        System.out.println("The result of evaluating expression " + convertToString(e, notation));
        System.out.println("is: " + _eval(e) + ".");
    }

    public void print(Expression e, Notation notation, int radix) {
        System.out.println("\n\nThe result of evaluating expression " + convertToString(e, notation));
        System.out.println("is: " + eval(e).asNumber().toString(radix) + ".");
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


    public CalculatorResult _eval(Expression e) {

        // create a new visitor to evaluate expressions
        Evaluator v = new Evaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        try {
            e.accept(v);
        } catch (ArithmeticException d) {
            return CalculatorResult.UNDEFINED;
        }
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    public CalculatorResult eval(Expression e) {
        ch.execute(e);
        actionHistory.add(e);
        return _eval(e);
    }

    public void printLog(Notation n) {
        for (Expression e : actionHistory) {
            System.out.println(convertToString(e, n));
        }
    }

    public List<Expression> getActionHistory() {
        return actionHistory;
    }

    public Expression getCurrent() {
        return ch.getCurrent();
    }

    public void undo() throws NoActionLeftInHistory {
        ch.undo();
        if(!actionHistory.isEmpty()) {
            actionHistory.remove(actionHistory.size()-1);
        }

    }

    public void redo() throws NoActionLeftInHistory {
        ch.redo();
        if(ch.getCurrent() != null) {
            actionHistory.add(ch.getCurrent());
        }

    }



    public Expression getMemory(int indexOfMemory) throws CalculatorMemoryError {
        if (memory.length <= indexOfMemory || indexOfMemory < 0)
            throw new CalculatorMemoryError();
        return memory[indexOfMemory];
    }

    public void setMemory(Expression e, int indexOfMemory) throws CalculatorMemoryError {
        if (memory.length <= indexOfMemory || indexOfMemory < 0)
            throw new CalculatorMemoryError();
        memory[indexOfMemory] = e;
    }

    public void clearHistory() {
        actionHistory.clear();
    }

    public void saveHistory() {
        try {
            FileOutputStream file = new FileOutputStream("history.bin");
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(actionHistory);

            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadHistory() {
        List<Expression> loadedHistory = null;
        try {
            FileInputStream file = new FileInputStream("history.bin");
            ObjectInputStream in = new ObjectInputStream(file);

            loadedHistory = (List<Expression>) in.readObject();

            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (loadedHistory != null) {

            this.actionHistory.addAll(loadedHistory);
        }
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

    public Double convertUnit(double amount, String from, String to) {
       return converter.convertUnit(amount, from, to);
    }

    public Double convertCurrency(double amount, String from, String to) throws IOException {
        return converter.convertCurrency(amount, from, to);
    }


    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
