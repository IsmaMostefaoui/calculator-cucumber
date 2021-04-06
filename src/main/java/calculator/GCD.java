package calculator;

import java.math.BigInteger;
import java.util.List;

final public class GCD extends Operation {

    public /*constructor*/ GCD(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "gcd";
        neutral = BigInteger.ONE;
        modulo = new BigInteger(Integer.MAX_VALUE + "");
    }

    public /*constructor*/ GCD(List<Expression> elist, MyNumber modulo) throws IllegalConstruction {
        super(elist);
        symbol = "gcd";
        neutral = BigInteger.ONE;
        this.modulo = modulo.getValue();
    }

    public GCD(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "/";
        neutral = BigInteger.ONE;
    }

    public BigInteger op(BigInteger l, BigInteger r) {
        return (l.gcd(r).mod(modulo));
    }
}
