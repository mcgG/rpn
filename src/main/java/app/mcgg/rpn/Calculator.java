package app.mcgg.rpn;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.processor.Lookup;
import app.mcgg.rpn.processor.Parser;
import app.mcgg.rpn.util.RPNStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;


/*
 What Calculator do is add, plus .... stack is not a logic part in calculator

 */
@Service
public class Calculator {

    @Autowired
    private Parser parser;

    @Autowired
    private Lookup lookup;

    @Autowired
    private RPNStack stack;

    /*
        1. Check if there is an empty stack: stack.isEmpty()
        2. Search for operator object;
     */
    private void processOperator(String operator) throws CalculatorException {

        Operator op = lookup.getMap().get(operator);
        if (op == null) {
            System.out.println("[debug] " + stack.toString());
            throw new CalculatorException(String.format(
                    "operator %s (position: %d): invalid operator",
                    operator,
                    stack.getSp()));
        }

        // Undo
        if (op.getClass().getSimpleName().equals("Undo")) {
            stack.undo();
            System.out.println("[debug] " + stack.toString());
            return;
        }

        // Clear
        System.out.println(op.getClass().getSimpleName());
        if (op.getClass().getSimpleName().equals("Clear")) {
            stack.clear();
            System.out.println("[debug] " + stack.toString());
            return;
        }

        // To check number is sufficient or not
        if (!stack.checkOperandQuantity(op)) {
            throw new CalculatorException(String.format(
                    "operator %s (position: %d): insufficient parameters",
                    operator,
                    stack.getSp() + op.getRequiredOperandsNumber()));
        }

        BigDecimal[] operands = stack.getOperands(op);
        BigDecimal rs;
        try {
            rs = op.calculate(operands[0], operands[1]);
        } catch (CalculatorException e) {
            throw new CalculatorException(String.format(
                    "operator %s (position: %d): %s",
                    operator,
                    stack.getSp() + op.getRequiredOperandsNumber(),
                    e.getMessage()));
        }

        if (rs != null) {
            stack.push(rs);
        }
        System.out.println("[debug] " + stack.toString());
    }

    private void processElement(String element) throws CalculatorException{
        BigDecimal value = parser.parseDouble(element);
        if (value == null) {
            processOperator(element);
        } else {
            stack.push(value);
            System.out.println("[debug] " + stack.toString());
        }
    }
    
    public boolean eval(String formula) {
        String[] items = formula.split("\\s+");
        System.out.println("|************************************|");
        System.out.println(Arrays.toString(items));
        for (String item : items) {
            try {
                processElement(item);
            } catch (CalculatorException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

}
