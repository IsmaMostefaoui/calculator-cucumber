package visitor;

import calculator.*;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Stringator extends Visitor {
    private String toStringValue;
    private Notation notation;

    public String getString(Expression e, Notation notation) {
        this.notation = notation;
        e.accept(this);
        return toStringValue;
    }

    @Override
    public void visit(MyNumber n) {
        toStringValue = n.getRepresentation() + "_{" + n.getRadix() + "}";
    }

    @Override
    public void visit(Converter c) {
        toStringValue = c.getRepresentation() + "_{" + c.getRadix() + "}";
    }

    @Override
    public void visit(RandomGenerator r) {
        toStringValue = "random_" + r.getValue();
    }

    @Override
    public void visit(Operation o) {
        ArrayList<String> toStringArgs = new ArrayList<>();
        for (Expression a : o.args) {
            a.accept(this);
            toStringArgs.add(toStringValue);
        }
        Supplier<Stream<String>> streamSupplier = toStringArgs::stream;
        switch (notation) {
            case INFIX:
                toStringValue = "( " +
                        streamSupplier.get().reduce((s1, s2) -> s1 + " " + o.getSymbol() + " " + s2).get() +
                        " )";
                break;
            case PREFIX:
                toStringValue = o.getSymbol() + " " +
                        "(" +
                        streamSupplier.get().reduce((s1, s2) -> s1 + ", " + s2).get() +
                        ")";
                break;
            case POSTFIX:
                toStringValue = "(" +
                        streamSupplier.get().reduce((s1, s2) -> s1 + ", " + s2).get() +
                        ")" +
                        " " + o.getSymbol();
                break;
            default:
                toStringValue = "This case should never occur.";
        }
    }
}
