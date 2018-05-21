package app.mcgg.rpn.util;

import app.mcgg.rpn.operator.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

@Component
public class RPNStack {

    private Stack<BigDecimal> stack = new Stack<>();
    private Stack<BigDecimal> history = new Stack<>();
    private int sp = 0;

    public void clear() {
        sp = 0;
        stack.clear();
        history.clear();
    }

    public BigDecimal pop(boolean isUndoOperation) {
        BigDecimal tmp = stack.pop();
        if (!isUndoOperation) {
            history.push(tmp);
        } else {
            history.pop();
            while (! history.isEmpty() && history.peek() != null) {
                stack.push(history.pop());
            }
        }
        sp++;
        return tmp;
    }

    public void push(BigDecimal num) {
        stack.push(num);
        history.push(null);
        sp++;
    }

    public Stack<BigDecimal> getStack() {
        return stack;
    }

    public int getSp() {
        return sp;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public String toString() {
        ArrayList<Object> arrayList = new ArrayList<>(Arrays.asList(stack.toArray()));
        for (int i=0; i<arrayList.size(); i++) {
            arrayList.set(i,((BigDecimal) arrayList.get(i))
                    .setScale(10, RoundingMode.FLOOR)
                    .stripTrailingZeros()
                    .toPlainString());
        }
       String rs = arrayList.toString();
       return rs.substring(1, rs.length()-1);

    }
}
