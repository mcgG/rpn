package app.mcgg.rpn.util;

import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class BeanCache {

    private ApplicationContextProvider applicationContextProvider;

    @Value("${operator.operand}")
    private String[] operand;

    @Value("${operator.stack}")
    private String[] stack;

    public BeanCache(ApplicationContextProvider applicationContextProvider) {
        this.applicationContextProvider = applicationContextProvider;
    }

    private Map<String, Operator> map = new HashMap<>();

    private void setup(String[] ops) {
        for (String op : ops) {
            map.put(op, applicationContextProvider.getBeanByName(op));
        }
    }

    public Map<String, Operator> getMap() {
        if (map.isEmpty()) {
            setup(operand);
            setup(stack);
        }
        return map;
    }

}
