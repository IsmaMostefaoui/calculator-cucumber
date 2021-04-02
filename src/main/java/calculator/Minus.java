package calculator;

import java.time.Duration;
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

    public int op(int l, int r) {
        return (l - r);
    }


    public Duration op(MyDate l, MyDate r) {
        return Duration.between(l.getValue(), r.getValue());
    }
}
