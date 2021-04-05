package junit5tests;

import calculator.IllegalConstruction;
import calculator.MyDateTime;
import calculator.MyTimeDuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestMyTimeDuration {



    @Test
    public void testParsing() throws IllegalConstruction {
        MyTimeDuration date = new MyTimeDuration("10d 10h 15m 1s");

    }


}
