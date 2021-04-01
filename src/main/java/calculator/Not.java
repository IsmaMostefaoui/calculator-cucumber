package calculator;

import java.util.List;

final public class Not extends UnaryOperation {

    public Not(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "!";
        this.neutral = 1;
    }

    public Not(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "!";
        this.neutral = 1;
    }

    @Override
    public int op(int l, int r) {
        return (l != 0) ? 0 : 1;
    }
}
