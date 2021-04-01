package calculator;

import java.util.List;

final public class And extends Operation{


    public And(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "&";
        neutral = 1;
    }

    public And(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "&";
        neutral = 1;
    }

    @Override
    public int op(int l, int r) {
        return (l*r) != 0 ? 1 : 0;
    }
}
