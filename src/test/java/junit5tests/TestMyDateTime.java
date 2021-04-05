package junit5tests;

import calculator.IllegalConstruction;
import calculator.MyDateTime;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.time.Duration;


public class TestMyDateTime {



    @Test
    public void testDateParsing() throws IllegalConstruction {

        new MyDateTime("2020-11-05 11:10:10");
        new MyDateTime("2015-11-11");
        new MyDateTime("2015-11-11 05:27 AM");


    }

    @Test
    public void testTimeZoneParsing() throws IllegalConstruction {

        MyDateTime date1 = new MyDateTime("05:27 PM UTC+2");
        MyDateTime date2 = new MyDateTime("05:27 PM UTC+0");
        assertEquals(Duration.ofHours(2), Duration.between(date1.getValue(), date2.getValue()));
    }

    @Test
    public void testParseFail() {
        assertThrows(IllegalConstruction.class, () -> {new MyDateTime("20220-12-10 10:10:10");});
    }

}
