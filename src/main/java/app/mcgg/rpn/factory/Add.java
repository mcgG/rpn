package app.mcgg.rpn.factory;

import java.math.BigDecimal;

public class Add extends AbstractOperation {

    @Override
    public BigDecimal calculate() {
        return this.a.add(b);
    }
}
