package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.stereotype.Service;

@Service
public class Divide implements Calculator {

    @Override
    public double calculate(double x, double y) throws CalculatorException {
        if (y == 0) {
            throw new CalculatorException("Cannot divide by 0!");
        }
        return x / y;
    }
}
