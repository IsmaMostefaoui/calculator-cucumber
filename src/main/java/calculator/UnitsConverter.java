package calculator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UnitsConverter {


    public double convertCurrency(double amount, String inputCurrency, String outputCurrency) throws IOException {
        // api documentation https://www.frankfurter.app/docs/

        inputCurrency = inputCurrency.toUpperCase();
        outputCurrency = outputCurrency.toUpperCase();

        String path = "https://api.frankfurter.app/latest?amount=%f&from=%s&to=%s";

        URL url = new URL(String.format(path, amount, inputCurrency, outputCurrency));

        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();


        if (request.getResponseCode() != 200)
            throw new RuntimeException("Request ended with code: " + request.getResponseCode());

        Scanner sc = new Scanner(url.openStream());
        String json = sc.nextLine();


        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        return Double.parseDouble(jsonObject.get("rates").getAsJsonObject().get(outputCurrency).toString());
    }


    public Double convertUnit(double amount, String inputUnit, String outputUnit) {


        Unit from = Unit.findBySymbol(inputUnit);
        Unit to = Unit.findBySymbol(outputUnit);

        if (from != null) {
            if (to != null) {
                if (from.quantity.equals(to.quantity)) {

                    return amount * from.multiplier / to.multiplier;

                } else {
                    throw new RuntimeException("'" + inputUnit + "' and '" + outputUnit + "' should measure the same quantity");
                }
            } else {
                throw new RuntimeException("Unknown unit '" + outputUnit + "'");
            }
        } else {
            throw new RuntimeException("Unknown unit '" + inputUnit + "'");
        }


    }


}
