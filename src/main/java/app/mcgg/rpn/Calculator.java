package app.mcgg.rpn;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.processor.Lookup;
import app.mcgg.rpn.processor.Parser;
import app.mcgg.rpn.processor.StackProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class Calculator {

    @Autowired
    private Parser parser;

    @Autowired
    private Lookup lookup;

    @Autowired
    private StackProcessor stackProcessor;


    private void processOperator(String operator) throws CalculatorException {

        Operator op = lookup.getMap().get(operator);
        if (op == null) {
            throw new CalculatorException(operator, stackProcessor.getSP(), "invalid operator");
        }

        // Undo
        if (op.getClass().getSimpleName().contains("Undo")) {
            stackProcessor.undo();
            return;
        }

        // Clear
        if (op.getClass().getSimpleName().contains("Clear")) {
            stackProcessor.clear();
            return;
        }

        if (!stackProcessor.checkOperandQuantity(op)) {
            throw new CalculatorException(operator, stackProcessor.getSP() + op.getRequiredOperandsNumber(), "insufficient operands");
        }

        BigDecimal[] operands = stackProcessor.getOperands(op);
        BigDecimal rs;
        try {
            rs = op.calculate(operands[0], operands[1]);
        } catch (ArithmeticException e) {
            throw new CalculatorException(operator, stackProcessor.getSP() + op.getRequiredOperandsNumber(), e.getMessage());
        }

        if (rs != null) {
            stackProcessor.push(rs);
        }
    }

    private void evalElement(String element) throws CalculatorException {
        BigDecimal value = parser.parseDouble(element);
        if (value == null) {
            processOperator(element);
        } else {
            stackProcessor.push(value);
        }
    }
    
    public String eval(String formula) throws CalculatorException {
        String[] items = Arrays.stream(formula.split("\\s+"))
                        .filter(s -> !s.isEmpty()).toArray(String[]::new);
        for (String item : items) {
            evalElement(item);
        }
        return stackProcessor.getStackString();
    }

}
