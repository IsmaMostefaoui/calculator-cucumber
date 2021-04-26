package calculator.expression;

import calculator.error.IllegalConstruction;
import visitor.Visitor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyDateTime implements Expression {

    private ZonedDateTime value;

    public MyDateTime(ZonedDateTime dateTime) {
        this.value = dateTime;
    }

    public MyDateTime(String dateString) throws IllegalConstruction {

        dateString = dateString.toUpperCase();

        List<String> timeZoneFormats = Arrays.asList(
                "OOOO",
                "O",
                "zzzz",
                "zzz",
                "zz",
                "z",
                "XXXXX",
                "XXXX",
                "XXX",
                "XX",
                "X"
        );

        List<String> timeFormats = Arrays.asList("hh:mm[:ss] a", "HH:mm[:ss]");

        List<String> zonedTimeFormats = new ArrayList<>();

        for (String timeFormat : timeFormats) {
            for (String timeZoneFormat : timeZoneFormats) {
                zonedTimeFormats.add(timeFormat + "[ " + timeZoneFormat + "]");
            }
        }


        String dateFormat = "yyyy-MM-dd";


        List<String> fullDateFormats = new ArrayList<>();


        for (String zonedTimeFormat : zonedTimeFormats) {
            fullDateFormats.add(dateFormat + "[ " + zonedTimeFormat + "]");
        }
        fullDateFormats.addAll(zonedTimeFormats);


        for (String format : fullDateFormats) {
            ZonedDateTime now = ZonedDateTime.now();
            DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder()
                    .appendPattern(format)
                    .parseDefaulting(ChronoField.DAY_OF_MONTH, now.getDayOfMonth())
                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, now.getMonthValue())
                    .parseDefaulting(ChronoField.YEAR_OF_ERA, now.getYear())
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0);


            if (dateString.contains("PM") || dateString.contains("AM")) {
                formatterBuilder.parseDefaulting(ChronoField.CLOCK_HOUR_OF_AMPM, 0);
            } else {
                formatterBuilder.parseDefaulting(ChronoField.HOUR_OF_DAY, 0);
            }

            DateTimeFormatter formatter = formatterBuilder.toFormatter().withZone(ZoneId.systemDefault());


            try {

                this.value = ZonedDateTime.parse(dateString, formatter);
                return;
            } catch (DateTimeParseException ignored) {
            }
        }

        if (this.value == null) {
            throw new IllegalConstruction();
        }


    }


    public ZonedDateTime getValue() {
        return this.value;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


}
