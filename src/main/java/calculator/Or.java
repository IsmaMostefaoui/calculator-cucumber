package calculator;

import java.math.BigInteger;
import java.util.List;

final public class Or extends Operation {
    public Or(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "|";
        neutral = BigInteger.ZERO;
    }

    public Or(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "|";
        neutral = BigInteger.ZERO;
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        return !l.add(r).equals(BigInteger.ZERO) ? CalculatorResult.TRUE : CalculatorResult.FALSE;
    }
}
