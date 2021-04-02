package calculator;

import visitor.Visitor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDate implements  Expression {

    private LocalDateTime value;



    public MyDate(String dateString) throws IllegalConstruction {
        List<String> acceptedFormats = new ArrayList<>();
        acceptedFormats.add("yyyy-MM-dd hh:mm:ss a z");
        acceptedFormats.add("yyyy-MM-dd hh:mm a z");

        acceptedFormats.add("yyyy-MM-dd HH:mm:ss z");
        acceptedFormats.add("yyyy-MM-dd HH:mm z");

        acceptedFormats.add("yyyy-MM-dd z");

        acceptedFormats.add("hh:mm:ss a z");
        acceptedFormats.add("hh:mm:ss a");
        acceptedFormats.add("HH:mm:ss z");
        acceptedFormats.add("HH:mm:ss");


        acceptedFormats.add("hh:mm a z");
        acceptedFormats.add("hh:mm a");
        acceptedFormats.add("HH:mm z");
        acceptedFormats.add("HH:mm");


        for (String acceptedFormat : acceptedFormats) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(acceptedFormat);
            if(!acceptedFormat.contains("z")) {
                formatter = formatter.withZone(ZoneOffset.UTC);
            }

            try {
                this.value = LocalDateTime.parse(dateString, formatter);

            } catch (DateTimeParseException ignored) {
            }
        }
        if(this.value == null)
            throw new IllegalConstruction();

    }


    public LocalDateTime getValue() {
         return this.value;
    }
    @Override
    public void accept(Visitor v) {

    }


}
