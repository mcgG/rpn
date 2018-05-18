package app.mcgg.rpn.Test.factory;

import java.math.BigDecimal;

public class Multiply extends AbstractOperation {
    @Override
    public BigDecimal calculate() {
        return this.a.multiply(b);
    }
}
