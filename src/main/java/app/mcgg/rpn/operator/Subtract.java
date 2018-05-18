package app.mcgg.rpn.operator;

import org.springframework.stereotype.Service;

@Service("-")
public class Subtract implements Operator {

    private int requiredOperandsNumber = 2;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public double calculate(double x, double y) {
        return x - y;
    }
}
