package calculator;

import visitor.Visitor;

import java.time.Duration;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTimeDuration implements  Expression {



    private Duration value;

    public MyTimeDuration(Duration duration) {
        this.value = duration;
    }

    public MyTimeDuration(String inputString) throws IllegalConstruction {

        String pattern = "^"+ "((\\d+)d)?" + " ?" + "((\\d+)h)?" + " ?" + "((\\d+)m)?" + " ?" + "((\\d+)s)?" + "$";
        System.out.println(pattern);
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputString);
        if(m.matches()) {


            long days = Long.parseLong(m.group(2));
            long hours = Long.parseLong(m.group(4));
            long minutes = Long.parseLong(m.group(6));
            long seconds = Long.parseLong(m.group(8));


            this.value = Duration.ofDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);
            System.out.println(this.value);

        } else {
            throw new IllegalConstruction();
        }

    }




    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Duration getValue() {
        return this.value;
    }
}
