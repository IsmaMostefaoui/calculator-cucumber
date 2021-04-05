package calculator;

import java.time.Duration;
import java.time.ZonedDateTime;

public class CalculatorResult {

    public enum ResultType {NUMBER, DURATION, DATETIME, UNDEFINED}

    public static  final  CalculatorResult UNDEFINED = new CalculatorResult();

    private final ResultType type;


    private Double computedValue;
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

    public CalculatorResult(Double computedValue) {
        this.type = ResultType.NUMBER;
        this.computedValue = computedValue;
    }

    public boolean isUndefined() {
        return this.type == ResultType.UNDEFINED;
    }


    public  Double  asNumber() {
        if(this.type == ResultType.NUMBER) {
            return this.computedValue;
        }
        throw new ClassCastException();
    }

    public  Duration  asDuration() {
        if(this.type == ResultType.DURATION) {
            return this.durationValue;
        }
        throw new ClassCastException();
    }

    public  ZonedDateTime  asZonedDateTime() {
        if(this.type == ResultType.DATETIME) {
            return this.zonedDatetimeValue;
        }
        throw new ClassCastException();
    }


    public ResultType getType() {
        return this.type;
    }



}
