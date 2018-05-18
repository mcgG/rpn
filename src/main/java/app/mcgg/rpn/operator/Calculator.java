package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;

public interface Calculator {

    double calculate(double x, double y) throws CalculatorException;
}
