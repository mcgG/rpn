package app.mcgg.rpn.operator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("undo")
public class Undo implements Operator {

    private int requiredOperandsNumber = 1;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal y) {
        throw new ArithmeticException("Invalid operation!");
    }
}
