package calculator;

import visitor.Visitor;

import java.math.BigInteger;

public class MyNumber implements Expression {
    private final BigInteger value;
    private final String representation;
    private final int radix;
    private final BigInteger modulo;
    private final boolean positive;

    public /*constructor*/ MyNumber(String v) throws NumberFormatException {
        modulo = new BigInteger(Integer.MAX_VALUE + "");
        value = new BigInteger(v).mod(modulo);
        representation = v;
        radix = 10;
        positive = true;
    }

    public /*constructor*/ MyNumber(String v, boolean positive) throws NumberFormatException {
        modulo = new BigInteger(Integer.MAX_VALUE + "");
        if (positive) {
            value = new BigInteger(v).mod(modulo);
        } else {
            value = new BigInteger(v).mod(modulo).negate();
        }
        representation = v;
        radix = 10;
        this.positive = positive;
    }

    public /*constructor*/ MyNumber(String v, String modulo) throws NumberFormatException {
        this.modulo = new BigInteger(modulo);
        value = new BigInteger(v).mod(this.modulo);
        representation = v;
        radix = 10;
        positive = true;
    }

    public /*constructor*/ MyNumber(String v, String modulo, boolean positive) throws NumberFormatException {
        this.modulo = new BigInteger(modulo);
        if (positive) {
            value = new BigInteger(v).mod(this.modulo);
        } else {
            value = new BigInteger(v).mod(this.modulo).negate();
        }
        representation = v;
        radix = 10;
        this.positive = positive;
    }

    public /*constructor*/ MyNumber(String v, int radix) throws InnapropriateBase, NumberFormatException {
        if (radix < 2 || radix > 36) {
            throw new InnapropriateBase();
        }
        modulo = new BigInteger(Integer.MAX_VALUE + "");
        value = new BigInteger(v, radix).mod(modulo);
        representation = v;
        this.radix = radix;
        positive = true;
    }

    public BigInteger getValue() {
        return value;
    }

    public int getRadix() {
        return radix;
    }

    public String getRepresentation() {
        return representation;
    }

    public BigInteger getModulo() {
        return modulo;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    //Two MyNumber expressions are equal if the values they contain are equal
    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }

        // If the object is of another type then return false
        if (!(o instanceof MyNumber)) {
            return false;
        }
        return this.value.equals(((MyNumber) o).value);
        // I used == above since the contained value is a primitive value
        // If it had been a Java object, .equals() would be needed
    }

    // The method hashCode() needs to be overridden if the equals method is overridden; otherwise there may be problems when you use your object in hashed collections such as HashMap, HashSet, LinkedHashSet
    @Override
    public int hashCode() {
        return value.intValue();
    }

}
