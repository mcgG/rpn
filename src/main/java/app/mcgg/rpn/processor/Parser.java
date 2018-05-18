package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Parser {

    @Autowired
    private Lookup lookup;

    public void parse(String formula) {
        Operator add = lookup.getMap().get("+");
        try {
            System.out.println(add.calculate(1, 2));
        } catch (CalculatorException e) {
            e.printStackTrace();
        }
    }

    private Double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void processElement(String element, boolean isUndoOperation) {
        Double value = parseDouble(element);
        if (value == null) {
            processElement(element, isUndoOperation);
        } else {

        }
    }

    private void processOperator(String operator, boolean isUndoOperation) throws CalculatorException {

//        if (stack.isEmpty()) {
//            throw new CalculatorException("Stack is empty");
//        }
//
//        Operator op = lookup.getMap().get(operator);
//
//        if (op == null) {
//            throw new CalculatorException("Invalid operator!");
//        }
//
//        if (op.getRequiredOperandsNumber() > stack.size()) {
//            throw new CalculatorException("Operands are not sufficient!");
//        }

        //Double first = stack.pop();



    }

    private void eval(String formula) throws CalculatorException {
        eval(formula, false);
    }

    private void eval(String formula, boolean isUndoOperation) throws CalculatorException {
        if (formula == null) {
            throw new CalculatorException("Input cannot be null!");
        }
        String[] items = formula.split("\\s");
        for (String item : items) {
            processElement(item, isUndoOperation);
        }
    }





}
