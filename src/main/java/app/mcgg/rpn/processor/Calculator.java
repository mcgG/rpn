package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.util.BeanCache;
import app.mcgg.rpn.util.Parser;
import app.mcgg.rpn.processor.StackProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class Calculator {

    @Autowired
    private FormulaProcessor processor;

    
    public String eval(String formula) throws CalculatorException {
        String[] items = Arrays.stream(formula.split("\\s+"))
                        .filter(s -> !s.isEmpty()).toArray(String[]::new);
        for (String item : items) {

            if (processor.processElement(item) != null) { continue; }

            String operator = processor.processOperator(item);

            if (operator != null)
                processor.processCalculation(operator);
        }
        return processor.getStack();
    }

}
