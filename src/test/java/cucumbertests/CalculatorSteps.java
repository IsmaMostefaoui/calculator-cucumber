package cucumbertests;

import calculator.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class CalculatorSteps {

    private ArrayList<Expression> params;
    private Operation op;
    private Calculator c;

    @Before
    public void resetMemoryBeforeEachScenario() {
        params = null;
        op = null;
    }

    @Given("I initialise a calculator")
    public void givenIInitialiseACalculator() {
        c = new Calculator();
    }

    @Given("an integer operation {string}")
    public void givenAnIntegerOperation(String s) {
        // Write code here that turns the phrase above into concrete actions
        params = new ArrayList<>(); // create an empty set of parameters to be filled in
        try {
            switch (s) {
                case "+": {
                    op = new Plus(params);
                    break;
                }
                case "-": {
                    op = new Minus(params);
                    break;
                }
                case "*": {
                    op = new Times(params);
                    break;
                }
                case "/": {
                    op = new Divides(params);
                    break;
                }
                default: {
                    fail();
                }
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    // The following example shows how to use a DataTable provided as input.
    // (The example looks slightly complex, since DataTables can take as input
    //  tables in two dimensions, i.e. rows and lines. This is why the input
    //  is a list of lists.
    @Given("the following list of integer numbers")
    public void givenTheFollowingListOfNumbers(List<List<String>> numbers) {
        params = new ArrayList<>();
        // Since we only use one line of input, we use get(0) to take the first line of the list,
        // which is a list of strings, that we will manually convert to integers:
        numbers.get(0).forEach(n -> params.add(new MyNumber(n)));
        params.forEach(n -> System.out.println("value =" + n));
        op = null;
    }

    // The string in the Given annotation shows how to use regular expressions...
    // In this example, the notation d+ is used to represent numbers, i.e. nonempty sequences of digits
    @Given("^the sum of two numbers (\\d+) and (\\d+)$")
    // The alternative, and in this case simpler, notation would be:
    // @Given("the sum of two numbers {int} and {int}")
    public void givenTheSum(String n1, String n2) {
        try {
            params = new ArrayList<>();
            params.add(new MyNumber(n1));
            params.add(new MyNumber(n2));
            op = new Plus(params);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Then("^its (.*) notation is (.*)$")
    public void thenItsNotationIs(String notation, String s) {
        if (notation.equals("PREFIX") || notation.equals("POSTFIX") || notation.equals("INFIX")) {
            op.notation = Notation.valueOf(notation);
            assertEquals(s, op.toString());
        } else fail(notation + " is not a correct notation! ");
    }

    @When("^I provide a (.*) number (\\d+)$")
    public void whenIProvideANumber(String s, String val) {
        params.add(new MyNumber(val));
    }

    @Then("^the (.*) is (\\d+)$")
    public void thenTheOperationIs(String s, int val) {
        try {
            switch (s) {
                case "sum": {
                    op = new Plus(params);
                    break;
                }
                case "product": {
                    op = new Times(params);
                    break;
                }
                case "quotient": {
                    op = new Divides(params);
                    break;
                }
                case "difference": {
                    op = new Minus(params);
                    break;
                }
                default:
                    fail();
            }
            assertEquals(val, c.eval(op));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Then("the operation evaluates to {int}")
    public void thenTheOperationEvaluatesTo(int val) {
        //During previous @When steps, extra parameters may have been added to the operation
        //so we complete its parameter list here:
        op.addMoreParams(params);
        assertEquals(val, c.eval(op));
    }

    @When("I provide {int} and {int} for division")
    public void whenIProvideAndForDivision(String val1, String val2) {
        params.add(new MyNumber(val1));
        params.add(new MyNumber(val2));
    }

    @Then("the operation handles arithmetic error")
    public void thenTheOperationHandlesArithmeticError() {
        op.addMoreParams(params);
        try {
            c.eval(op);
        } catch (ArithmeticException e) {
            fail();
        }
        assertTrue(true);
    }

}
