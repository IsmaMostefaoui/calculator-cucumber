package visitor;


import calculator.expression.*;
import calculator.expression.operation.Operation;
import calculator.expression.operation.UnaryOperation;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(MyNumber n);

    public abstract void visit(Converter n);

    public abstract void visit(Operation o);

    public  void visit(UnaryOperation o) {
        this.visit((Operation) o);
    }

    public abstract void visit(RandomGenerator r);

    public abstract void visit(MyDateTime myDateTime);

    public abstract void visit(MyTimeDuration myTimeDuration);

}
