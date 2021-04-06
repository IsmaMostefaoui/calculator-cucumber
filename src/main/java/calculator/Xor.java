package calculator;

import java.math.BigInteger;
import java.util.List;

final public class Xor extends Operation {

    public Xor(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "||";
        this.neutral = BigInteger.ONE;
    }

    public Xor(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "||";
        this.neutral = BigInteger.ONE;
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        BigInteger lBool = !l.equals(BigInteger.ZERO) ? BigInteger.ONE : BigInteger.ZERO;
        BigInteger rBool = !r.equals(BigInteger.ZERO) ? BigInteger.ONE : BigInteger.ZERO;
        return lBool.add(rBool).compareTo(BigInteger.ONE) > 0 ? CalculatorResult.FALSE : CalculatorResult.TRUE;

    }
}
