package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.stereotype.Service;

@Service
public class Clear implements Calculator {
    @Override
    public double calculate(double x, double y) throws CalculatorException {
        throw new CalculatorException("Invalid operation!");
    }
}
