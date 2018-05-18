package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;

public interface Operator {

    double calculate(double x, double y) throws CalculatorException;
    public int getRequiredOperandsNumber();
}
