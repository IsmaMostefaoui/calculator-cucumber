package calculator;

import java.math.BigInteger;
import java.util.List;

final public class Exponents extends Operation {

    public /*constructor*/ Exponents(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "^";
        neutral = BigInteger.ONE;
        modulo = new BigInteger(Integer.MAX_VALUE + "");
    }

    public /*constructor*/ Exponents(List<Expression> elist, MyNumber modulo) throws IllegalConstruction {
        super(elist);
        symbol = "^";
        neutral = BigInteger.ONE;
        this.modulo = modulo.getValue();
    }

    public Exponents(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "^";
        neutral = BigInteger.ONE;
    }

    public CalculatorResult op(BigInteger l, BigInteger r) {
        return new CalculatorResult(l.modPow(r, modulo));
    }
}

