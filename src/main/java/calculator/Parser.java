package calculator;

import calculator.antlr4.ArithmeticLexer;
import calculator.antlr4.ArithmeticParser;
import org.antlr.v4.runtime.*;
import visitor.ArithmeticParserVisitor;

public class Parser {

    public Expression parse(String input) {
        CharStream inputStream = CharStreams.fromString(input);
        ArithmeticLexer arithmeticLexer = new ArithmeticLexer(inputStream);
        ArithmeticParser arithmeticParser = new ArithmeticParser(new CommonTokenStream(arithmeticLexer));

        arithmeticParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                throw new IllegalStateException("failed to parse at line " + line + " due to " + msg, e);
            }
        });

        ArithmeticParserVisitor visitor = new ArithmeticParserVisitor();
        return  visitor.visit(arithmeticParser.expr());
    }
}
