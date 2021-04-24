package calculator;

import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class UnaryMinus extends UnaryOperation {

    public UnaryMinus(Expression e) throws IllegalConstruction {
        this(Collections.singletonList(e));
    }
    public UnaryMinus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "-";

    }

    public UnaryMinus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "-";
    }

    @Override
    public CalculatorResult op(Duration r) {
        return new CalculatorResult( ZonedDateTime.now().minus(r));
    }
}
