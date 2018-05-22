package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.util.BeanCache;
import app.mcgg.rpn.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FormulaProcessor {

    @Autowired
    private Parser parser;

    @Autowired
    private BeanCache lookup;

    @Autowired
    private StackProcessor stackProcessor;

    protected BigDecimal processElement(String element) throws CalculatorException {
        BigDecimal value = parser.parseDouble(element);
        if (value != null) {
            stackProcessor.push(value);
        }
        return value;
    }

    protected String processOperator(String operator) throws CalculatorException {

        Operator op = lookup.getMap().get(operator);
        if (op == null) {
            throw new CalculatorException(operator, stackProcessor.getSP(), "invalid operator");
        }

        // Undo
        if (op.getClass().getSimpleName().contains("Undo")) {
            stackProcessor.undo();
            return null;
        }

        // Clear
        if (op.getClass().getSimpleName().contains("Clear")) {
            stackProcessor.clear();
            return null;
        }

        if (!stackProcessor.checkOperandQuantity(op)) {
            throw new CalculatorException(operator, stackProcessor.getSP() + op.getRequiredOperandsNumber(), "insufficient operands");
        }
        return operator;
    }

    protected void processCalculation(String operator) throws CalculatorException {
        Operator op = lookup.getMap().get(operator);
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

    protected String getStack() {
        return stackProcessor.getStackString();
    }
}
