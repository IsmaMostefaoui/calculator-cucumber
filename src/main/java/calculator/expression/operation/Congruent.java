package calculator.expression.operation;

import calculator.*;
import calculator.error.IllegalConstruction;
import calculator.expression.Expression;
import calculator.expression.MyNumber;

import java.math.BigInteger;
import java.util.List;

final public class Congruent extends Operation {


    public Congruent(List<Expression> elist, MyNumber modulo) throws IllegalConstruction {
        super(elist);
        symbol = "=%";
        neutral = BigInteger.ZERO;
        this.modulo = modulo.getValue();
    }

    public Congruent(List<Expression> elist, Notation n, MyNumber modulo) throws IllegalConstruction {
        super(elist, n);
        symbol = "=%";
        neutral = BigInteger.ZERO;
        this.modulo = modulo.getValue();
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        return l.mod(modulo).equals(r.mod(modulo)) ? CalculatorResult.TRUE : CalculatorResult.FALSE;
    }
}
