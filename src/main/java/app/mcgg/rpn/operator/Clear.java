package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("clear")
public class Clear implements Operator {

    private int requiredOperandsNumber = 0;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal y) throws CalculatorException {
        throw new CalculatorException("Invalid operation!");
    }
}
