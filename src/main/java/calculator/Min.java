package calculator;

import java.math.BigInteger;
import java.util.List;

final public class Min extends Operation {

    public /*constructor*/ Min(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "min";
    }

    public Min(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "min";
    }

    public BigInteger op(BigInteger l, BigInteger r) {
        return (l.min(r));
    }
}
