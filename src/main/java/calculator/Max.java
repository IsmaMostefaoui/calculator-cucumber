package calculator;

import java.math.BigInteger;
import java.util.List;

final public class Max extends Operation {

    public /*constructor*/ Max(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "max";
    }

    public Max(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "max";
    }

    public CalculatorResult op(BigInteger l, BigInteger r) {
        return new CalculatorResult(l.max(r));
    }
}
