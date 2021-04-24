package calculator;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class UnaryPlus extends UnaryOperation{

    public UnaryPlus(Expression e) throws IllegalConstruction {
        this(Collections.singletonList(e));
    }
    public UnaryPlus(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "+";
    }

    public UnaryPlus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "+";
    }

    @Override
    public CalculatorResult op(Duration r) {
        return new CalculatorResult( ZonedDateTime.now().plus(r));
    }
}
