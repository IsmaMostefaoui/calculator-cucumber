package visitor;

import calculator.*;

import java.util.ArrayList;

public class Evaluator extends Visitor {

    protected int computedValue;

    public Integer getResult() { return computedValue; }

    public void visit(MyNumber n) {
        computedValue = n.getValue();
    }

    public void visit(Operation o) throws DivisionByZero{
        ArrayList<Integer> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        int temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            System.out.println("temp = " + temp);
            temp = o.op(temp,evaluatedArgs.get(counter));
        }
        if (evaluatedArgs.size() == 1) {
            // unary operation
            temp = o.op(temp, -1);
        }
        // store the accumulated result
        computedValue = temp;
    }

}