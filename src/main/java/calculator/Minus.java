package calculator;


import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

final public class Minus extends Operation {

    public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "-";
        neutral = BigInteger.ZERO;
        modulo = new BigInteger(Integer.MAX_VALUE + "");
    }

    public /*constructor*/ Minus(List<Expression> elist, MyNumber modulo) throws IllegalConstruction {
        super(elist);
        symbol = "-";
        neutral = BigInteger.ZERO;
        this.modulo = modulo.getValue();
    }

    public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "-";
        neutral = BigInteger.ZERO;
    }


    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        return new CalculatorResult(l.subtract(r).mod(modulo));
    }

    @Override
    public CalculatorResult op(ZonedDateTime l, ZonedDateTime r) {
        return new CalculatorResult(Duration.between(l, r));
    }

    @Override
    public CalculatorResult op(ZonedDateTime l, Duration r) {
        return new CalculatorResult(l.minus(r));
    }


    @Override
    public CalculatorResult op(Duration l, Duration r) {
        return new CalculatorResult(l.minus(r));
    }


}
