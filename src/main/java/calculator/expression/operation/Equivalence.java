package calculator.expression.operation;

import calculator.CalculatorResult;
import calculator.error.IllegalConstruction;
import calculator.Notation;
import calculator.expression.Expression;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

final public class Equivalence extends Operation {
    public Equivalence(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "<=>";
        this.neutral = BigInteger.ONE;
    }

    public Equivalence(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "<=>";
        this.neutral = BigInteger.ONE;
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        try {
            Not not = new Not(Arrays.asList());
            Or or = new Or(Arrays.asList());
            And and = new And(Arrays.asList());
            return and.op(or.op(not.op(l).asNumber(), r), or.op(not.op(r).asNumber(), l));
        } catch (IllegalConstruction e) {
            e.printStackTrace();
        }
        return CalculatorResult.UNDEFINED;
    }
}
