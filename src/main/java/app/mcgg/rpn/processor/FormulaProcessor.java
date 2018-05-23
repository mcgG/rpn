package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.util.BeanCache;
import app.mcgg.rpn.util.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.EmptyStackException;

@Service
public class FormulaProcessor {

    /**
     * Parser:
     * A tool to parse double value
     */
    private Parser parser;

    /**
     * BeanCache:
     * Where cached all operator instances
     */
    private BeanCache lookup;

    /**
     *  StackProcessor:
     *  A tool to interact with RPNStack
     */
    private StackProcessor stackProcessor;

    public FormulaProcessor(Parser parser, BeanCache lookup, StackProcessor stackProcessor) {
        this.parser = parser;
        this.lookup = lookup;
        this.stackProcessor = stackProcessor;
    }

    /**
     *
     * @param element
     * @return Decimal value or null
     * @throws CalculatorException
     *
     * If element is a number then return it and push it into RPNStack
     * If element is not a number then return null
     */
    protected BigDecimal processElement(String element) throws CalculatorException {
        BigDecimal value = parser.parseDouble(element);
        if (value != null) {
            stackProcessor.push(value);
        }
        return value;
    }

    /**
     *
     * @param operator
     * @return operator
     * @throws CalculatorException
     *
     *  Try to get operator instance and handle undo, clear manipulation
     */
    protected String processOperator(String operator) throws CalculatorException {

        Operator op = lookup.getMap().get(operator);
        if (op == null) {
            throw new CalculatorException(operator, stackProcessor.getSP(), "invalid operator");
        }

        // Undo
        if (operator.equals("undo")) {
            try {
                stackProcessor.undo();
            } catch (EmptyStackException e) {
                throw new CalculatorException(operator, stackProcessor.getSP(), "insufficient operands");
            }
            return null;
        }

        // Clear
        if (operator.equals("clear")) {
            stackProcessor.clear();
            return null;
        }

        if (!stackProcessor.checkOperandQuantity(op)) {
            throw new CalculatorException(operator, stackProcessor.getSP() + op.getRequiredOperandsNumber(), "insufficient operands");
        }
        return operator;
    }

    /**
     *
     * @param operator
     * @return Result String
     * @throws CalculatorException
     */
    protected BigDecimal processCalculation(String operator) throws CalculatorException {
        Operator op = lookup.getMap().get(operator);
        BigDecimal[] operands = stackProcessor.getOperands(op);
        BigDecimal rs;
        try {
            rs = op.calculate(operands[0], operands[1]);
        } catch (ArithmeticException e) {
            throw new CalculatorException(operator, stackProcessor.getSP() + 1, e.getMessage());
        }

        if (rs != null) {
            stackProcessor.push(rs);
        }
        return rs;
    }

    protected String getStack() {
        return stackProcessor.getStackString();
    }
}
