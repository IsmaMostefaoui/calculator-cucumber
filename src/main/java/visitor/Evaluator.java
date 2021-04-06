package visitor;

import calculator.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class Evaluator extends Visitor {

    private BigInteger computedValue;

    public BigInteger getResult() {
        return computedValue;
    }

    public void visit(MyNumber n) {
        computedValue = n.getValue();
    }

    public void visit(Converter c) {
        computedValue = c.getValue();
    }

    public void visit(RandomGenerator c) {
        computedValue = c.getValue();
    }

    public void visit(Operation o) throws DivisionByZero {
        ArrayList<BigInteger> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for (Expression a : o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults
        BigInteger temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for (int counter = 1; counter < max; counter++) {
            temp = o.op(temp, evaluatedArgs.get(counter));
        }
        // store the accumulated result
        computedValue = temp;
    }

}
