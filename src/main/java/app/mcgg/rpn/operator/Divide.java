package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service("/")
public class Divide implements Operator {

    @Value("${decimal.precision}")
    private int precision;

    private int requiredOperandsNumber = 2;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal y) throws CalculatorException {
        if (y.compareTo(BigDecimal.ZERO) == 0) {
            throw new CalculatorException("Cannot divide by 0!");
        }
        return x.divide(y, new MathContext(precision));
    }
}
