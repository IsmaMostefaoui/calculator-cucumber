package calculator;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

final public class Plus extends Operation {

    public /*constructor*/ Plus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "+";
        neutral = 0;
    }

    public Plus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "+";
        neutral = 0;
    }

    @Override
    public CalculatorResult op(Double l, Double r) {
        return new CalculatorResult(l + r);
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
