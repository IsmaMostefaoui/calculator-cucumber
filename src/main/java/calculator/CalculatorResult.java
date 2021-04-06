package calculator;

import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;

public class CalculatorResult {

    public static final CalculatorResult UNDEFINED = new CalculatorResult();
    public static final CalculatorResult TRUE = new CalculatorResult(BigInteger.ONE);
    public static final CalculatorResult FALSE = new CalculatorResult(BigInteger.ZERO);
    private final ResultType type;
    private BigInteger computedValue;
    private Duration durationValue;
    private ZonedDateTime zonedDatetimeValue;
    private CalculatorResult() {
        this.type = ResultType.UNDEFINED;
    }

    public CalculatorResult(ZonedDateTime zonedDatetimeValue) {
        this.type = ResultType.DATETIME;
        this.zonedDatetimeValue = zonedDatetimeValue;
    }

    public CalculatorResult(Duration durationValue) {
        this.type = ResultType.DURATION;
        this.durationValue = durationValue;
    }


    public CalculatorResult(BigInteger computedValue) {
        this.type = ResultType.NUMBER;
        this.computedValue = computedValue;
    }

    public boolean isUndefined() {
        return this.type == ResultType.UNDEFINED;
    }

    public BigInteger asNumber() {
        if (this.type == ResultType.NUMBER) {
            return this.computedValue;
        }
        throw new ClassCastException();
    }

    public Duration asDuration() {
        if (this.type == ResultType.DURATION) {
            return this.durationValue;
        }
        throw new ClassCastException();
    }

    public ZonedDateTime asZonedDateTime() {
        if (this.type == ResultType.DATETIME) {
            return this.zonedDatetimeValue;
        }
        throw new ClassCastException();
    }

    public ResultType getType() {
        return this.type;
    }

    public enum ResultType {NUMBER, DURATION, DATETIME, UNDEFINED}


}
