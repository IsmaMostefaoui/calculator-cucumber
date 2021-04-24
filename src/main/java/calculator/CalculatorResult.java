package calculator;

import java.math.BigInteger;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
        System.out.println(this.type);
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

    public String toString() {
        switch (this.type) {

            case NUMBER:
                return this.computedValue.toString();
            case DURATION:
                return String.format("%dd %dh %dm %ds"
                        , this.durationValue.toDays()
                        , this.durationValue.toHours()
                        , this.durationValue.toMinutes()
                        , this.durationValue.toSeconds());

            case DATETIME:
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss z");
                return this.zonedDatetimeValue.format(formatter2);
            case UNDEFINED:
                return "UNDEFINED";
        }
        throw new RuntimeException("Unexpected result type: "+this.type);
    }

    public ResultType getType() {
        return this.type;
    }

    public enum ResultType {NUMBER, DURATION, DATETIME, UNDEFINED}


}
