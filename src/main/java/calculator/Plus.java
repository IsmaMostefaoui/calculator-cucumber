package calculator;


import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

final public class Plus extends Operation {

    public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "+";
        neutral = BigInteger.ZERO;
        modulo = new BigInteger(Integer.MAX_VALUE + "");
    }

    public /*constructor*/ Plus(List<Expression> elist, MyNumber modulo) throws IllegalConstruction {
        super(elist);
        symbol = "+";
        neutral = BigInteger.ZERO;
        this.modulo = modulo.getValue();
    }

    public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "+";
        neutral = BigInteger.ZERO;
    }

    @Override
    public CalculatorResult op(BigInteger l, BigInteger r) {
        return new CalculatorResult(l.add(r).mod(modulo));
    }


    @Override
    public CalculatorResult op(ZonedDateTime l, Duration r) {
        return new CalculatorResult(l.plus(r));
    }

    @Override
    public CalculatorResult op(Duration r) {
        return new CalculatorResult(ZonedDateTime.now().plus(r));
    }

    @Override
    public CalculatorResult op(Duration l, ZonedDateTime r) {
        return this.op(r, l);
    }

    @Override
    public CalculatorResult op(Duration l, Duration r) {
        return new CalculatorResult(l.plus(r));
    }
}
