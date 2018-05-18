package app.mcgg.rpn.config;

import app.mcgg.rpn.operator.Add;
import app.mcgg.rpn.operator.Calculator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Operator {

    Operator() {
    }

    private static final Map<String, Calculator> lookup = new HashMap<>();

    static {
        lookup.put("+", new Add());
    }

    public Map<String, Calculator> getOperator() {
        return null;
    }



}
