package app.mcgg.rpn.processor;

import app.mcgg.rpn.operator.Calculator;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @Value("${operator.enable}")
    private String[] operators;

    private Map<String, Calculator> operatorMap = new HashMap<>();

    @Autowired
    @Qualifier("add")
    private Calculator add;

    @Autowired
    @Qualifier("subtract")
    private Calculator subtract;

    Field[] fields = null;

    @Bean
    public Map<String, Calculator> getOperations() {

        for (String s : operators) {
            //operatorMap.put(s, )
        }
        return null;
    }
}
