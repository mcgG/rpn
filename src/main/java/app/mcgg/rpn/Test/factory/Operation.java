package app.mcgg.rpn.Test.factory;

import java.math.BigDecimal;

public interface Operation {

    void setParam(BigDecimal a, BigDecimal b);
    BigDecimal calculate();

    void setParam(BigDecimal a);
}