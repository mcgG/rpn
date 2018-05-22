package app.mcgg.rpn.processor;

import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.util.RPNStack;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StackProcessor {

    private RPNStack stack;

    public StackProcessor(RPNStack stack) {
        this.stack = stack;
    }

    public boolean checkOperandQuantity(Operator op) {
        return op.getRequiredOperandsNumber() <= stack.size();
    }

    public void push(BigDecimal rs) {
        stack.push(rs);
    }

    public BigDecimal[] getOperands(Operator op) {
        BigDecimal[] operands = new BigDecimal[2];
        if (op.getRequiredOperandsNumber() > 1) {
            operands[1] = stack.pop(false);
            operands[0] = stack.pop(false);
        } else {
            operands[0] = stack.pop(false);
            operands[1] = null;
        }
        return operands;
    }

    public void undo() {
        stack.pop(true);
    }

    public void clear() {
        stack.clear();
    }

    public int getSP() {
        return stack.getSp();
    }

    public String getStackString() {
        return stack.toString();
    }
}
