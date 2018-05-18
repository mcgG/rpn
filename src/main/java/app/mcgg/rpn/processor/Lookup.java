package app.mcgg.rpn.processor;

import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Lookup {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Value("${operator.enable}")
    private String[] operators;

    private Map<String, Operator> map = new HashMap<>();

    public Map<String, Operator> getMap() {
        if (map.isEmpty()) {
            for (String op : operators) {
                map.put(op, (Operator) applicationContextProvider.getBeanByName(op));
            }
        }
        return this.map;
    }

}
