package app.mcgg.rpn.util;

import app.mcgg.rpn.operator.Operator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  This bean is to load all operator instances into a HashMap.
 */

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
