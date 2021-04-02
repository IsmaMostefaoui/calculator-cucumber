package visitor;


import calculator.*;
import calculator.antlr4.ArithmeticBaseVisitor;
import calculator.antlr4.ArithmeticParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArithmeticParserVisitor extends ArithmeticBaseVisitor<Expression> {
    @Override
    public Expression visitNumber(ArithmeticParser.NumberContext ctx) {
        return new MyNumber(Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public Expression visitMulDiv(ArithmeticParser.MulDivContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.expr(0)), visit(ctx.expr(1)) );

        try {
            if (ctx.op.getType() == ArithmeticParser.TIMES) {
                return new Times(params);
            } else {
                return new Divides(params);
            }
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    @Override
    public Expression visitAddSub(ArithmeticParser.AddSubContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.expr(0)), visit(ctx.expr(1)) );
        try {
            if (ctx.op.getType() == ArithmeticParser.PLUS) {
                return new Plus(params);
            } else {
                return new Minus(params);
            }
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    @Override
    public Expression visitParens(ArithmeticParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
