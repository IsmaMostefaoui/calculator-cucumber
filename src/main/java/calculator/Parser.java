package calculator;

import calculator.antlr4.CalculatorLexer;
import calculator.antlr4.CalculatorParser;
import calculator.antlr4.CalculatorVisitor;
import calculator.error.SyntaxError;
import calculator.expression.Expression;
import org.antlr.v4.runtime.*;
import visitor.CalculatorParserVisitor;

public class Parser {

    private final CalculatorVisitor<Expression> parserVisitor;

    private boolean is_errored = false;

    public Parser(Calculator c) {
        this.parserVisitor =  new CalculatorParserVisitor(c);
    }

    public Expression parse(String input) throws SyntaxError {

        CharStream inputStream = CharStreams.fromString(input);
        CalculatorLexer arithmeticLexer = new CalculatorLexer(inputStream);
        CalculatorParser arithmeticParser = new CalculatorParser(new CommonTokenStream(arithmeticLexer));

        arithmeticParser.removeErrorListeners();
        arithmeticLexer.removeErrorListeners();
        arithmeticLexer.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)   {

                is_errored = true;
            }
        });



        arithmeticParser.addErrorListener(new BaseErrorListener() {

            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)   {
                is_errored = true;
            }
        });



        Expression e = this.parserVisitor.visit(arithmeticParser.start());

        if (is_errored) {
            is_errored = false;
           throw  new SyntaxError();
        }
        return e;

    }
}
