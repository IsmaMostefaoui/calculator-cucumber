package calculator.expression.operation;

import calculator.CalculatorResult;
import calculator.expression.Expression;
import calculator.error.IllegalConstruction;
import calculator.Notation;

import java.math.BigInteger;
import java.util.List;

final public class Min extends Operation {

    public /*constructor*/ Min(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "min";
    }

    public Min(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "min";
    }

    public CalculatorResult op(BigInteger l, BigInteger r) {
        return new CalculatorResult(l.min(r));
    }
}
