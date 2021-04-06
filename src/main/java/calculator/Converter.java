package calculator;

import visitor.Visitor;

import java.math.BigInteger;

public class Converter implements Expression {

    MyNumber value;
    String representation;
    int radix;

    public Converter(MyNumber value, int radix) throws NullPointerException, InnapropriateBase {
        if (radix < 2 || radix > 36) {
            throw new InnapropriateBase();
        } else {
            this.value = value;
            this.radix = radix;
            this.representation = value.getValue().toString(this.radix);
        }
    }

    public Converter(BigInteger value, int radix) throws NullPointerException, InnapropriateBase {
        if (radix < 2 || radix > 36) {
            throw new InnapropriateBase();
        } else {
            this.value = new MyNumber(value.toString());
            this.radix = radix;
            this.representation = value.toString(radix);
        }
    }

    public BigInteger getValue() {
        return value.getValue();
    }

    public MyNumber getNumber() {
        return value;
    }

    public int getRadix() {
        return radix;
    }

    public String getRepresentation() {
        return representation;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
