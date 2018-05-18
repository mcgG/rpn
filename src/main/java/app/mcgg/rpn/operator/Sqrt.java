package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.stereotype.Service;

@Service
public class Sqrt implements Calculator {

    @Override
    public double calculate(double x, double y) throws CalculatorException{
        if (x < 0) {
            throw new CalculatorException("Cannot square root negative number!");
        }
        return Math.sqrt(x);
    }
}
