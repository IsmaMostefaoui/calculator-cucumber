package calculator;

import calculator.expression.Expression;

import java.util.Scanner;

public class Main {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    private static void perr(String err) {
        System.out.println(ANSI_RED + err + ANSI_RESET);
    }


    public static void main(String[] args) {


        String promptIndicator = ">> ";
        String resultPrefix = " ";


        Scanner in = new Scanner(System.in);

        int memory = -1;
        while (memory == -1) {
            System.out.println("Combien d'espace mémoire desirez vous?");
            System.out.print(promptIndicator);
            try {
                memory = Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                perr("Veuillez enter un nombre entier.");
            }
        }


        Calculator c = new Calculator(memory);

        Parser parser = new Parser(c);


        while (true) {


            try {
                System.out.print(promptIndicator);
                String input = in.nextLine().trim();
                if (!input.equals("")) {
                    switch (input) {
                        case "exit":
                            return;
                        case "undo":
                            c.undo();
                            break;
                        case "history":
                            c.printLog(Notation.INFIX);
                            break;
                        case "redo":
                            c.redo();
                            break;
                        case "save":
                            c.saveHistory();
                            break;
                        case "load":
                            c.loadHistory();
                            break;
                        default:

                            Expression e = parser.parse(input);

                            if (e != null) {
                                CalculatorResult result = c.eval(e);
                                System.out.println(resultPrefix + result);
                            }


                            break;
                    }

                }

            } catch (Exception e) {
                perr("Quelque chose s'est mal passé :/");
            }


        }
    }
}
