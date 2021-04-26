package calculator;

import calculator.error.NoActionLeftInHistory;
import calculator.expression.Expression;

import java.io.Serializable;
import java.util.Stack;

public class CommandHandler implements Serializable {


    private Stack<Expression> actionQueue;
    private Stack<Expression> actionQueueReverse;

    private Expression currentAction;

    public CommandHandler() {
        currentAction = null;
        actionQueue = new Stack<>();
        actionQueueReverse = new Stack<>();
    }

    public Expression getCurrent() {
        return currentAction;
    }

    public void execute(Expression e) {
        if (currentAction != null) {
            actionQueue.push(currentAction);
        }
        currentAction = e;
        actionQueueReverse.clear();
    }

    public void undo() throws NoActionLeftInHistory {
        if(currentAction != null && actionQueue.size() == 0) {
            actionQueueReverse.push(currentAction);
            currentAction = null;
        } else if (actionQueue.size() == 0 ) {
            throw new NoActionLeftInHistory();
        } else {
            actionQueueReverse.push(currentAction);
            currentAction = actionQueue.pop();
        }

    }

    public void redo() throws NoActionLeftInHistory {
        if (actionQueueReverse.size() == 0)
            throw new NoActionLeftInHistory();
        actionQueue.push(currentAction);
        currentAction = actionQueueReverse.pop();
    }

}
