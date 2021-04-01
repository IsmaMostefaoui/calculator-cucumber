package calculator;

import java.util.Arrays;
import java.util.List;

public class Equivalence extends Operation{
    public Equivalence(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        this.symbol = "<=>";
        this.neutral = 1;
    }

    public Equivalence(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        this.symbol = "<=>";
        this.neutral = 1;
    }

    @Override
    public int op(int l, int r) {
        try{
            Not not = new Not(Arrays.asList());
            Or or = new Or(Arrays.asList());
            And and = new And(Arrays.asList());
            return and.op(or.op(not.op(l, -1), r), or.op(not.op(r, -1), l));
        }catch (IllegalConstruction e){
            e.printStackTrace();
        }return 0;
    }
}
