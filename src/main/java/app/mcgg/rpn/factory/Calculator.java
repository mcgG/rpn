package app.mcgg.rpn.factory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    private Map<String, String> map = new HashMap<>();

    private String operator;
    private BigDecimal a, b;

    public void setParam(String operator, BigDecimal a, BigDecimal b){
        this.operator = operator;
        this.a = a;
        this.b = b;
        map.put("+", "app.mcgg.rpn.factory.Add");
    }

    public BigDecimal calc(){
        OperationFactory factory = new OperationFactory(map.get(operator));
        Operation op = factory.getOperation();
        op.setParam(a, b);
        return op.calculate();
    }

}
