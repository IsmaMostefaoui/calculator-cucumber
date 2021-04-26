package junit5tests;

import calculator.error.IllegalConstruction;
import calculator.expression.MyTimeDuration;
import org.junit.jupiter.api.Test;


public class TestMyTimeDuration {


    @Test
    public void testParsing() throws IllegalConstruction {
        MyTimeDuration date = new MyTimeDuration("10d 10h 15m 1s");

    }


}
