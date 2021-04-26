package calculator.expression.operation;

import calculator.*;
import calculator.error.IllegalConstruction;
import calculator.expression.Expression;
import calculator.expression.MyNumber;

import java.math.BigInteger;
import java.util.List;

final public class Times extends Operation {

    public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "*";
        neutral = BigInteger.ONE;
        modulo = new BigInteger(Integer.MAX_VALUE + "");
    }

    public /*constructor*/ Times(List<Expression> elist, MyNumber modulo) throws IllegalConstruction {
        super(elist);
        symbol = "*";
        neutral = BigInteger.ONE;
        this.modulo = modulo.getValue();
    }

    public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "*";
        neutral = BigInteger.ONE;
    }


    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        return new CalculatorResult(l.multiply(r).mod(modulo));

    }


}
