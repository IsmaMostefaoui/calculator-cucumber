package calculator.expression.operation;

import calculator.CalculatorResult;
import calculator.expression.Expression;
import calculator.error.IllegalConstruction;
import calculator.Notation;

import java.math.BigInteger;
import java.util.List;

final public class Not extends UnaryOperation {

    public Not(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "!";
        this.neutral = BigInteger.ONE;
    }

    public Not(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "!";
        this.neutral = BigInteger.ONE;
    }

    @Override
    public CalculatorResult op(BigInteger l) {
        return !l.equals(BigInteger.ZERO) ? CalculatorResult.FALSE : CalculatorResult.TRUE;
    }
}
