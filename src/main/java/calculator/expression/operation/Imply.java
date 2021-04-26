package calculator.expression.operation;

import calculator.CalculatorResult;
import calculator.expression.Expression;
import calculator.error.IllegalConstruction;
import calculator.Notation;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

final public class Imply extends Operation {
    public Imply(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "=>";
        this.neutral = BigInteger.ONE;
    }

    public Imply(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "=>";
        this.neutral = BigInteger.ONE;
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        try {
            Not not = new Not(Arrays.asList());
            Or or = new Or(Arrays.asList());
            return or.op(not.op(l).asNumber(), r);
        } catch (IllegalConstruction e) {
            e.printStackTrace();
        }
        return CalculatorResult.UNDEFINED;

    }
}
