package calculator;

import java.util.Arrays;
import java.util.List;

final public class Imply extends Operation {
    public Imply(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "=>";
        this.neutral = 1;
    }

    public Imply(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "=>";
        this.neutral = 1;
    }

    @Override
    public int op(int l, int r) {
        try {
            Not not = new Not(Arrays.asList());
            Or or = new Or(Arrays.asList());
            return or.op(not.op(l, -1), r);
        } catch (IllegalConstruction e) {
            e.printStackTrace();
        }
        return 0;
        //int notL = (l != 0)? 0 : 1;
        //return (notL+r) != 0 ? 1 : 0;
    }
}
