package calculator.expression;

import calculator.error.IllegalConstruction;
import visitor.Visitor;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTimeDuration implements Expression {


    private final Duration value;

    public MyTimeDuration(Duration duration) {
        this.value = duration;
    }

    public MyTimeDuration(String inputString) throws IllegalConstruction {

        String pattern = "^" + "((\\d+)d)?" + " ?" + "((\\d+)h)?" + " ?" + "((\\d+)m)?" + " ?" + "((\\d+)s)?" + "$";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputString);
        if (m.matches()) {


            long days = Long.parseLong(m.group(2));
            long hours = Long.parseLong(m.group(4));
            long minutes = Long.parseLong(m.group(6));
            long seconds = Long.parseLong(m.group(8));


            this.value = Duration.ofDays(days).plusHours(hours).plusMinutes(minutes).plusSeconds(seconds);

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
