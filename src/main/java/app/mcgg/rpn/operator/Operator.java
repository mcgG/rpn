package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;

import java.math.BigDecimal;

public interface Operator {

    BigDecimal calculate(BigDecimal x, BigDecimal y) throws CalculatorException;
    int getRequiredOperandsNumber();
}
