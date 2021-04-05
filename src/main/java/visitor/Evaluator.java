package visitor;

import calculator.*;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Evaluator extends Visitor {

    private CalculatorResult computedValue;


    public CalculatorResult getResult() { return computedValue; }



    public void visit(MyNumber n) {
        computedValue = new CalculatorResult(Double.valueOf(n.getValue()));
    }

    public void visit(Operation o) throws DivisionByZero{
        ArrayList<CalculatorResult> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accummulate all the evaluated subresults


        CalculatorResult temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            temp = o.op(temp,evaluatedArgs.get(counter));
        }
        // store the accumulated result
        computedValue = temp;
    }

    @Override
    public void visit(MyDateTime myDateTime) {
        this.computedValue = new CalculatorResult( myDateTime.getValue());
    }

    @Override
    public void visit(MyTimeDuration myTimeDuration) {
        this.computedValue = new CalculatorResult(myTimeDuration.getValue());
    }

}
