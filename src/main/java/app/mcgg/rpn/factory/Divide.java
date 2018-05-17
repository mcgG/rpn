package app.mcgg.rpn.factory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Divide extends AbstractOperation{

    @Override
    public BigDecimal calculate() {
        return this.a.divide(b, 10, RoundingMode.FLOOR);
    }
}
