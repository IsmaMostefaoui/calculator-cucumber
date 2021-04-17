package visitor;

import calculator.*;
import calculator.antlr4.CalculatorBaseVisitor;
import calculator.antlr4.CalculatorParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CalculatorParserVisitor extends CalculatorBaseVisitor<Expression> {

    private  Calculator c;

    @Override
    public Expression visitBasedNumber(CalculatorParser.BasedNumberContext ctx) {
        try {
            return new MyNumber(ctx.NUMBER(0).getText(), Integer.parseInt(ctx.NUMBER(1).getText()));
        } catch (InnapropriateBase innapropriateBase) {
            throw new RuntimeException();
        }

    }


    public CalculatorParserVisitor(Calculator c) {
        this.c = c;
    }

    @Override
    public Expression visitSaveM(CalculatorParser.SaveMContext ctx) {
        c.setMemory(visit(ctx.expr()), Integer.parseInt(ctx.NUMBER().getText()));
        return null;
    }

    @Override
    public Expression visitLoadM(CalculatorParser.LoadMContext ctx) {
        return c.getMemory(Integer.parseInt(ctx.NUMBER().getText()));
    }

    @Override
    public Expression visitImply(CalculatorParser.ImplyContext ctx) {

        try {
            return new Imply(Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1))));
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }

    }

    @Override
    public Expression visitEquiv(CalculatorParser.EquivContext ctx) {
        try {
            return new Equivalence(Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1))));
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    @Override
    public Expression visitConvert(CalculatorParser.ConvertContext ctx) {

        CalculatorResult r =  c.eval(visit(ctx.expr()));

        try {
            Converter c = new Converter(r.asNumber(), Integer.parseInt(ctx.NUMBER().getText()));
            System.out.println(c.getRepresentation());
        } catch ( InnapropriateBase illegalConstruction) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public Expression visitXor(CalculatorParser.XorContext ctx) {
        try {
            return new Xor(Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1))));
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }

    }

    @Override
    public Expression visitCalculation(CalculatorParser.CalculationContext ctx) {
        CalculatorParser.ExprContext expr = ctx.expr();
        return expr.isEmpty() ? null : visit(expr);
    }


    @Override
    public Expression visitRand(CalculatorParser.RandContext ctx) {
        return new RandomGenerator(ctx.NUMBER().getText());
    }



    @Override
    public Expression visitNot(CalculatorParser.NotContext ctx) {
        try {
            return new Not(Collections.singletonList(visit(ctx.expr())));
        } catch (IllegalConstruction illegalConstruction) {
            illegalConstruction.printStackTrace();
        }
        return super.visitNot(ctx);
    }

    @Override
    public Expression visitOr(CalculatorParser.OrContext ctx) {
        try {
            return new Or(Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1))));
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    @Override
    public Expression visitNumber(CalculatorParser.NumberContext ctx) {
        return new MyNumber(ctx.NUMBER().getText());

    }

    @Override
    public Expression visitMulDiv(CalculatorParser.MulDivContext ctx) {
        List<Expression> params = Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1)));

        try {
            switch (ctx.op.getType()) {
                case CalculatorParser.TIMES:
                    return new Times(params);
                case CalculatorParser.DIV:
                    return new Divides(params);
                case CalculatorParser.MODINV:
                    return new ModularInverse(params);
                default:
                    throw new RuntimeException();
            }

        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    @Override
    public Expression visitAddSub(CalculatorParser.AddSubContext ctx) {
        List<Expression> params = new ArrayList<>();
        Collections.addAll(params, visit(ctx.expr(0)), visit(ctx.expr(1)) );
        try {
            if (ctx.op.getType() == CalculatorParser.PLUS) {
                return new Plus(params);
            } else {
                return new Minus(params);
            }
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }
    }

    @Override
    public Expression visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Expression visitAnd(CalculatorParser.AndContext ctx) {
        try {
            return new And(Arrays.asList(visit(ctx.expr(0)), visit(ctx.expr(1))));
        } catch (IllegalConstruction illegalConstruction) {
            throw new RuntimeException();
        }

    }

    @Override
    public Expression visitUnaryPlus(CalculatorParser.UnaryPlusContext ctx) {
        return super.visitUnaryPlus(ctx);
    }

    @Override
    public Expression visitUnaryMinus(CalculatorParser.UnaryMinusContext ctx) {
        return super.visitUnaryMinus(ctx);
    }

    @Override
    public Expression visitDateOrDuration(CalculatorParser.DateOrDurationContext ctx) {
        String text = ctx.STRING().getText().trim().replaceAll("^'|'$", "");
        try {
            return new MyDateTime(text);
        } catch (IllegalConstruction ignored) {
            try {
                return new MyTimeDuration(text);
            } catch (IllegalConstruction illegalConstruction) {
                throw new RuntimeException();
            }
        }

    }

}
