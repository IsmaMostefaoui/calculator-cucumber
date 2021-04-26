package calculator.expression;

import visitor.Visitor;

import java.math.BigInteger;
import java.util.Random;

public class RandomGenerator implements Expression {
    private final BigInteger value;

    public /*constructor*/ RandomGenerator(String v) throws NumberFormatException {
        BigInteger upperLimit = new BigInteger(v);
        BigInteger temp;
        do {
            temp = new BigInteger(upperLimit.bitLength(), new Random());
        } while (temp.compareTo(upperLimit) >= 0);
        value = temp;
    }

    public BigInteger getValue() {
        return value;
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
        return this.value.equals(o);
        // I used == above since the contained value is a primitive value
        // If it had been a Java object, .equals() would be needed
    }

    // The method hashCode() needs to be overridden if the equals method is overridden; otherwise there may be problems when you use your object in hashed collections such as HashMap, HashSet, LinkedHashSet
    @Override
    public int hashCode() {
        return value.intValue();
    }

}
