package app.mcgg.rpn.factory;

import java.math.BigDecimal;

public class Substract extends AbstractOperation {
    @Override
    public BigDecimal calculate() {
        return this.a.subtract(b);
    }
}
