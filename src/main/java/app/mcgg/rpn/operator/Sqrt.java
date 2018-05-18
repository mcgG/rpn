package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.stereotype.Service;

@Service("sqrt")
public class Sqrt implements Operator {

    private int requiredOperandsNumber = 1;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public double calculate(double x, double y) throws CalculatorException{
        if (x < 0) {
            throw new CalculatorException("Cannot square root negative number!");
        }
        return Math.sqrt(x);
    }
}
