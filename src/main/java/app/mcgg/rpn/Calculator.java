package app.mcgg.rpn;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.processor.Lookup;
import app.mcgg.rpn.processor.Parser;
import app.mcgg.rpn.processor.StackProcessor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;


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
    private StackProcessor stackProcessor;


    private void processOperator(String operator) throws CalculatorException {

        Operator op = lookup.getMap().get(operator);
        if (op == null) {
//            System.out.println("[debug] " + stackProcessor.getStackString());
            throw new CalculatorException(operator, stackProcessor.getSP(), "invalid operator");
        }

        // Undo
        if (op.getClass().getSimpleName().contains("Undo")) {
            stackProcessor.undo();
//            System.out.println("[debug] " + stackProcessor.getStackString());
            return;
        }

        // Clear
        if (op.getClass().getSimpleName().contains("Clear")) {
            stackProcessor.clear();
//            System.out.println("[debug] " + stackProcessor.getStackString());
            return;
        }

        // To check number is sufficient or not
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
//        System.out.println("[debug] " + stackProcessor.getStackString());
    }

    private void processElement(String element) throws CalculatorException{
        BigDecimal value = parser.parseDouble(element);
        if (value == null) {
            processOperator(element);
        } else {
            stackProcessor.push(value);
//            System.out.println("[debug] " + stackProcessor.getStackString());
        }
    }
    
    public String eval(String formula) {
        String[] items = Arrays.stream(formula.split("\\s+"))
                        .filter(s -> !s.isEmpty()).toArray(String[]::new);
        System.out.println("|************************************|");
        System.out.println(Arrays.toString(items));
        for (String item : items) {
            try {
                processElement(item);
            } catch (CalculatorException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        }
        return stackProcessor.getStackString();
    }

}
