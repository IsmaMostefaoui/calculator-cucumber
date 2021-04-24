package calculator;

import visitor.Visitor;

import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public abstract class UnaryOperation extends Operation {



    public UnaryOperation(List<Expression> elist) throws IllegalConstruction {
        super(elist, Notation.PREFIX);
        if (elist.size() > 1)
            throw new IllegalConstruction();
    }

    public UnaryOperation(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public CalculatorResult op(CalculatorResult r) {
        switch (r.getType()) {
            case NUMBER:
                return this.op( r.asNumber());
            case DURATION:
                return this.op( r.asDuration());
            case DATETIME:
                return this.op( r.asZonedDateTime());
        }
        return CalculatorResult.UNDEFINED;
    }

    public CalculatorResult op(Duration r) {
        return CalculatorResult.UNDEFINED;
    }

    public CalculatorResult op(BigInteger r) {
        return CalculatorResult.UNDEFINED;
    }
    public CalculatorResult op(ZonedDateTime r) {
        return CalculatorResult.UNDEFINED;
    }


}