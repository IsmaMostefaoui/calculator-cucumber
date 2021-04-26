package calculator.expression.operation;

import calculator.*;
import calculator.error.IllegalConstruction;
import calculator.expression.Expression;

import java.math.BigInteger;
import java.util.List;

final public class And extends Operation {


    public And(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "&";
        neutral = BigInteger.ONE;
    }

    public And(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "&";
        neutral = BigInteger.ONE;
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        return !l.multiply(r).equals(BigInteger.ZERO) ? CalculatorResult.TRUE : CalculatorResult.FALSE;
    }
}
