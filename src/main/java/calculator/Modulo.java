package calculator;

import java.math.BigInteger;
import java.util.List;

final public class Modulo extends Operation {

    public /*constructor*/ Modulo(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "mod";
    }

    public Modulo(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "mod";
    }

    public BigInteger op(BigInteger l, BigInteger r) {
        return (l.mod(r));
    }
}
