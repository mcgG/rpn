package app.mcgg.rpn.Test.factory;

import java.math.BigDecimal;
import java.util.Stack;

public class Execute {

    private MyStack stack = new MyStack();
    private Stack<String> cacheStack = new Stack<>();

    public void parseFormula(String formula) {

        String[] items = formula.split(" ");
        for (String item : items) {
            System.out.println(item + " " + item == "+");
            if (item.equals("+")) {
                tryCal(item);
                System.out.println("-----------------------------");
                System.out.println(stack.toString());
            } else if(item == "-") {

            } else if(item == "*") {

            } else if(item == "/") {

            } else if(item == "sqrt") {

            } else if(item == "undo") {

            } else if(item == "clear") {

            } else {
                stack.push(item);
            }
        }
    }

    private void tryCal(String operator) {
        BigDecimal second = new BigDecimal(stack.pop());
        BigDecimal first = new BigDecimal(stack.pop());
        Calculator calculator = new Calculator();
        calculator.setParam(operator, first, second);
        BigDecimal rs = calculator.calc();
        stack.push(rs.toString());
    }

    private Object formateNumber(BigDecimal num) {
        Number tmp = num.intValue() - num.doubleValue() == 0 ? num.intValue() : num.doubleValue();
        if (num.intValue() - num.doubleValue() == 0) {
            return num.intValue();
        }
        return num.doubleValue();
    }
}
