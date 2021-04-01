package calculator;

import java.util.List;

final public class Xor extends Operation{

    public Xor(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "||";
        this.neutral = 1;
    }

    public Xor(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "||";
        neutral = 1;
    }

    @Override
    public int op(int l, int r) {
        int lBool = ((l!=0) ? 1 : 0);
        int rBool = ((r!=0)? 1 : 0);
        return (lBool+rBool>1) ? 0 : lBool+rBool;
    }
}
