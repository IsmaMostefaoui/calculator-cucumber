package calculator;

import calculator.antlr4.CalculatorLexer;
import calculator.antlr4.CalculatorParser;
import calculator.antlr4.CalculatorVisitor;
import org.antlr.v4.runtime.*;
import visitor.CalculatorParserVisitor;

public class Parser {

    private final CalculatorVisitor<Expression> parserVisitor;
    public Parser(Calculator c) {
        this.parserVisitor =  new CalculatorParserVisitor(c);
    }

    public Expression parse(String input) {
        CharStream inputStream = CharStreams.fromString(input);
        CalculatorLexer arithmeticLexer = new CalculatorLexer(inputStream);
        CalculatorParser arithmeticParser = new CalculatorParser(new CommonTokenStream(arithmeticLexer));

//        arithmeticParser.addErrorListener(new BaseErrorListener() {
//            @Override
//            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)   {
////                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
//            }
//        });


        return  this.parserVisitor.visit(arithmeticParser.start());

    }
}
