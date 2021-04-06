package calculator;

import java.util.List;

public abstract class UnaryOperation extends Operation {

    public UnaryOperation(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        if (elist.size() > 1)
            throw new IllegalConstruction();
    }

    public UnaryOperation(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
    }


}