package calculator;

import java.math.BigInteger;
import java.util.List;

final public class ModularInverse extends Operation {

    public /*constructor*/ ModularInverse(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "mod";
    }

    public ModularInverse(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "mod";
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) throws ArithmeticException {
        return new CalculatorResult(l.modInverse(r));
    }
}
