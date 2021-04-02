package junit5tests;

import calculator.IllegalConstruction;
import calculator.MyDate;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
public class TestMyDate {



    @Test
    public void testDateParsing() throws IllegalConstruction {
        MyDate date = new MyDate("2020-11-05 11:10:10");

    }

    @Test
    public void testTimeZoneParsing() throws IllegalConstruction {

        MyDate date1 = new MyDate("05:27 PM +0000");
        MyDate date2 = new MyDate("00:27 PM +0500");

        assertEquals(Duration.ZERO, Duration.between(date1.getValue(), date2.getValue()));
    }

    @Test
    public void testParseFail() {
        assertThrows(IllegalConstruction.class, () -> {new MyDate("20220-12-10 10:10:10");});
    }


}
