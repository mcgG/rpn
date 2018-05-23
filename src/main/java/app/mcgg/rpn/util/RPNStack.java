package app.mcgg.rpn.util;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

@Component
public class RPNStack {

    /**
     *  Main stack
     */
    private Stack<BigDecimal> stack = new Stack<>();

    /**
     *  History stack to store history
     */
    private Stack<BigDecimal> history = new Stack<>();

    /**
     *  Stack Pointer to mark the position
     */
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
        ArrayList<String> arrayList = new ArrayList<>();
        for (Object o : Arrays.asList(stack.toArray())) {
            arrayList.add( ( (BigDecimal) o )
                    .setScale(10, RoundingMode.FLOOR)
                    .stripTrailingZeros()
                    .toPlainString() );
        }
        return String.join(" ", arrayList);
    }
}
