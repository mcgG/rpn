package app.mcgg.rpn.factory;

import java.math.BigDecimal;

public abstract class AbstractOperation implements Operation {

    protected BigDecimal a, b;

    public void setParam(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    public void setParam(BigDecimal a) {
        this.a = a;
    }
}
