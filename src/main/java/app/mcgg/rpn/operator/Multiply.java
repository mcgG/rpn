package app.mcgg.rpn.operator;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("*")
public class Multiply implements Operator {

    private int requiredOperandsNumber = 2;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal y) {
        return x.multiply(y);
    }
}
