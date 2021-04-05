package calculator;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

final public class Minus extends Operation {

    public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "-";
        neutral = 0;
    }

    public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "-";
        neutral = 0;
    }

    @Override
    public CalculatorResult op(Double l, Double r) {
        return new CalculatorResult(l-r);
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
        return new CalculatorResult( l.minus(r));
    }

    @Override
    public CalculatorResult op(Duration r) {
        return new CalculatorResult(ZonedDateTime.now().minus(r));
    }
}
