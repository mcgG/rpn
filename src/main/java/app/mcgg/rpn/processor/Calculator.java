package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Calculator {

    private FormulaProcessor processor;

    public Calculator(FormulaProcessor processor) {
        this.processor = processor;
    }

    public String eval(String formula) throws CalculatorException {
        String[] items = Arrays.stream(formula.split("\\s+"))
                        .filter(s -> !s.isEmpty()).toArray(String[]::new);
        for (String item : items) {
            /**
             * If return value is not null means item is an operand,
             * and has been pushed into RPNStack in processElement method
             */
            if (processor.processElement(item) != null) { continue; }

            /**
             *  Try to get operator
             */
            String operator = processor.processOperator(item);

            /**
             *  If operator is null means its an invalid operator,
             *  an CalculatorException will be thrown here and Aspect
             *  will handle the Exception
             */
            if (operator != null)
                processor.processCalculation(operator);
        }
        return processor.getStack();
    }

}
