package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.Notation;
import calculator.Operation;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Stringator extends Visitor{
    private String toStringValue;
    private Notation notation;

    public String getString(Expression e, Notation notation){
        this.notation=notation;
        e.accept(this);
        return toStringValue;
    }

    @Override
    public void visit(MyNumber n) {
        toStringValue=Integer.toString(n.getValue());
    }

    @Override
    public void visit(Operation o) {
        ArrayList<String> toStringArgs=new ArrayList<>();
        for(Expression a:o.args){
            a.accept(this);
            toStringArgs.add(toStringValue);
        }
        Supplier<Stream<String>> streamSupplier= toStringArgs::stream;
        if (o.getSymbol().equals("!")){
            toStringValue = o.getSymbol() + " " + "(" + streamSupplier.get().reduce((s1, s2) -> s1).get() + ")";
        }else {
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
}
