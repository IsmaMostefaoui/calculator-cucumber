package visitor;

import calculator.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Stringator extends Visitor {
    private String toStringValue;
    private Notation notation;
    private MyDurationUnits durationUnits;


    public String getString(Expression e, Notation notation) {
        return this.getString(e, notation, null);
    }

    public String getString(Expression e, Notation notation, MyDurationUnits durationUnits) {
        this.notation = notation;
        this.durationUnits = durationUnits;
        e.accept(this);
        return toStringValue;
    }


    @Override
    public void visit(MyNumber n) {
        toStringValue = n.getRepresentation();
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
        if (o.getSymbol().equals("!")) {
            toStringValue = o.getSymbol() + " " + "(" + streamSupplier.get().reduce((s1, s2) -> s1).get() + ")";
        } else {
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

    @Override
    public void visit(MyDateTime myDateTime) {
        this.toStringValue = "[" + myDateTime.getValue().toString() + "]";
    }

    @Override
    public void visit(MyTimeDuration myTimeDuration) {

        Duration d = myTimeDuration.getValue();
        switch (this.durationUnits) {

            case DAYS:
                this.toStringValue = "[" + String.format("%dd %dh %dm %ds", d.toDays(), d.toHoursPart(), d.toMinutesPart(), d.toSecondsPart()) + "]";
                break;
            case HOURS:
                this.toStringValue = "[" + String.format("%dh %dm %ds", d.toHours(), d.toMinutesPart(), d.toSecondsPart()) + "]";
                break;
            case MINUTES:
                this.toStringValue = "[" + String.format("%dm %ds", d.toMinutes(), d.toSecondsPart()) + "]";
                break;
            case SECONDS:
                this.toStringValue = "[" + String.format("%ds", d.toSeconds()) + "]";
                break;


        }
    }
}
