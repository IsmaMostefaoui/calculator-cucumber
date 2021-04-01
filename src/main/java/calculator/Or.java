package calculator;

import java.util.List;

final public class Or extends Operation {
    public Or(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "|";
        neutral = 0;
    }

    public Or(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "|";
        neutral = 0;
    }

    @Override
    public int op(int l, int r) {
        return (l + r) != 0 ? 1 : 0;
    }
}
