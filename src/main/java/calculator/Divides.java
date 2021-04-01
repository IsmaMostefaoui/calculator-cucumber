package calculator;

import java.util.List;

final public class Divides extends Operation {

    public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "/";
        neutral = 1;
    }

    public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "/";
        neutral = 1;
    }

    public int op(int l, int r) {
        if (r == 0) {
            throw new DivisionByZero();
        }
        return (l / r);
    }
}
