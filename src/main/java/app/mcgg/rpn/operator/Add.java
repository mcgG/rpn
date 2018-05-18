package app.mcgg.rpn.operator;

import org.springframework.stereotype.Service;

@Service
public class Add implements Calculator {

    @Override
    public double calculate(double x, double y) {
        return x + y;
    }
}
