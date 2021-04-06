package visitor;

import calculator.*;

import java.util.ArrayList;
import java.util.Collections;

public class Countator extends Visitor {
    private int countDepth;
    private int countOps;
    private int countNbs;

    public Countator() {
        countDepth = 0;
        countOps = 0;
        countNbs = 0;
    }

    @Override
    public void visit(MyNumber n) {
        countNbs = 1;
        countDepth = 0;
    }

    @Override
    public void visit(Converter c) {
        countNbs = 1;
        countDepth = 0;
    }

    @Override
    public void visit(RandomGenerator r) {
        countNbs = 1;
        countDepth = 0;
    }

    @Override
    public void visit(Operation o) {
        ArrayList<Integer> depthArgs = new ArrayList<>();
        ArrayList<Integer> nbsArgs = new ArrayList<>();
        for (Expression a : o.args) {
            a.accept(this);
            depthArgs.add(countDepth + 1);
            nbsArgs.add(countNbs);
        }
        countDepth = Collections.max(depthArgs);
        countNbs = nbsArgs.stream().mapToInt(x -> x).reduce(Integer::sum).getAsInt();
        countOps += 1;
    }

    public int getCountNbs() {
        return countNbs;
    }

    public int getCountOps() {
        return countOps;
    }

    public int getCountDepth() {
        return countDepth;
    }
}
