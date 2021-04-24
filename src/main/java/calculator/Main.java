package calculator;

import visitor.Stringator;

import java.util.Scanner;

public class Main {


//    public static void main(String[] args) {
//        try {
//            MyNumber n = new MyNumber("101010", 2);
////            Stringator s = new Stringator();
//            System.out.println(n.getValue());
//            System.out.println(n.getRepresentation());
//            Converter c = new Converter(n, 8);
//            System.out.println(c.getValue());
//            System.out.println(c.getRepresentation());
//        } catch (InnapropriateBase innapropriateBase) {
//            innapropriateBase.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        Calculator c = new Calculator();

        Parser parser = new Parser(c);

        String promptIndicator = ">> ";
        String resultPrefix = " ";


        Scanner in = new Scanner(System.in);


        while(true) {
            System.out.print(promptIndicator);
            String input = in.nextLine().trim();
            if(!input.equals("")) {
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
                        try {
                            Expression e = parser.parse(input);


                            if(e != null) {
                                CalculatorResult result = c.eval(e);
                                System.out.println(resultPrefix + result);
                            }

                        }catch (IllegalStateException e) {
                            System.out.println("Invalid expression");
                        }
                        break;
                }

            }
        }
    }
}
