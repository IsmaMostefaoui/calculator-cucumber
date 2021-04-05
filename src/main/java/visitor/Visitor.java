package visitor;

import calculator.MyDateTime;
import calculator.MyNumber;
import calculator.MyTimeDuration;
import calculator.Operation;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(MyNumber n);
    public abstract void visit(Operation o);

    public abstract void visit(MyDateTime myDateTime);

    public abstract void visit(MyTimeDuration myTimeDuration);
}
