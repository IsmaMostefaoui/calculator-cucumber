package visitor;


import calculator.*;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(MyNumber n);

    public abstract void visit(Converter n);

    public abstract void visit(Operation o);

    public abstract void visit(RandomGenerator r);

    public abstract void visit(MyDateTime myDateTime);

    public abstract void visit(MyTimeDuration myTimeDuration);

}
