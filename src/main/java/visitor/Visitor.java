package visitor;

import calculator.Converter;
import calculator.MyNumber;
import calculator.Operation;
import calculator.RandomGenerator;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(MyNumber n);

    public abstract void visit(Converter n);

    public abstract void visit(Operation o);

    public abstract void visit(RandomGenerator r);
}
