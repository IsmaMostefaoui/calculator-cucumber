package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/***************************************
 * A very simple Calculator in Java    *
 * Tom Mens, February 2021             *
 * University of Mons - UMONS          *
 * Département d'Informatique          *
 * Faculté des Sciences                *
 ***************************************/

public class Main {

    public static void main(String[] args) {


        Calculator c = new Calculator();

        Parser parser = new Parser();

        String promptIndicator = ">> ";
        String resultPrefix = " ";

        Scanner in = new Scanner(System.in);

        while(true) {
            System.out.print(promptIndicator);
            String input = in.nextLine();

            try {
                Expression e = parser.parse(input);
                int result = c.eval(e);
                System.out.println(resultPrefix + result);
            } catch (IllegalStateException e) {
                System.out.println("Invalid expression");
            }


        }

    }

}
