package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.stereotype.Service;

@Service("undo")
public class Undo implements Operator {

    private int requiredOperandsNumber = 0;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public double calculate(double x, double y) throws CalculatorException {
        throw new CalculatorException("Invalid operation!");
    }
}
