package calculator;

import visitor.Visitor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public abstract class Operation implements Expression {
    public List<Expression> args;
    public Notation notation = Notation.INFIX; //by default, expressions are rendered as strings using infix notation
    protected String symbol;
    protected BigInteger neutral; // the neutral element of the operation (e.g. 1 for *, 0 for +)
    protected BigInteger modulo;

    // It is not allowed to create operation that have a null list of arguments.
    // Note that it is allowed to have an EMPTY list of arguments.
    public /*constructor*/ Operation(List<Expression> elist)
            throws IllegalConstruction {
        if (elist == null) {
            throw new IllegalConstruction();
        } else {
            args = new ArrayList<>(elist);
        }
    }

    public /*constructor*/ Operation(List<Expression> elist, Notation n)
            throws IllegalConstruction {
        this(elist);
        notation = n;
    }

    public List<Expression> getArgs() {
        return args;
    }

    abstract public BigInteger op(BigInteger l, BigInteger r);
    // the operation itself is specified in the subclasses

    // add more arguments to the existing list of arguments args
    public void addMoreParams(List<Expression> params) {
        args.addAll(params);
    }

    public void accept(Visitor v) {
        // and then visit the current operation itself
        v.visit(this);
    }

    //Two Operation expressions are equal if their list of arguments is equal and they are the same operation
    @Override
    public boolean equals(Object o) {
        if (o == null) return false; // No object should be equal to null

        if (this == o) return true; // If it's the same object, they're obviously equal

        if (getClass() != o.getClass())
            return false; // getClass() instead of instanceof() because an addition is not the same as a multiplication

        Operation other = (Operation) o;
        return this.args.equals(other.getArgs());
    }

    // The method hashCode also needs to be overridden it the equals method is overridden; otherwise there may be problems when you use your object in hashed collections such as HashMap, HashSet, LinkedHashSet
    @Override
    public int hashCode() {
        int result = 5, prime = 31;
        result = prime * result + neutral.intValue();
        result = prime * result + symbol.hashCode();
        result = prime * result + args.hashCode();
        return result;
    }

    public String getSymbol() {
        return symbol;
    }
}
