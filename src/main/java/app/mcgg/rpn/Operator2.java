package app.mcgg.rpn;

import app.mcgg.rpn.exception.CalculatorException;

import java.util.Arrays;

public enum Operator2 {

    ADD("+", 2) {
        public Double calculate(Double x, Double y){
            return x + y;
        }
    },
    SUBTRACT("-", 2) {
        public Double calculate(Double x, Double y) {
            return x - y;
        }
    },
    MULTIPLY("*", 2) {
        public Double calculate(Double x, Double y) {
            return x * y;
        }
    },
    DIVIDE("/", 2) {
        public Double calculate(Double x, Double y) throws CalculatorException {
            if (y == 0) {
                throw new CalculatorException("Cannot divide by 0 !");
            }
            return x / y;
        }
    },
    SQRT("sqrt", 1) {
        public Double calculate(Double x) throws CalculatorException {
            if (x < 0) {
                throw new CalculatorException("Square root cannot be negative value !");
            }
            return Math.sqrt(x);
        }
    },
    POWER("pow", 1) {
        public Double calculate(Double x) {
            return Math.pow(x, 2.0);
        }
    },
    UNDO("undo", 0) {

    },
    CLEAR("clear", 0) {

    };




    static {

    }

    private String operator;
    private int requiredParamNumber;

    Operator2(String operator, int requiredParamNumber) {
        this.operator = operator;
        this.requiredParamNumber = requiredParamNumber;
    }

    public static void main(String[] args) {

        for (Operator2 op : values()) {
            System.out.println(op + " " + op.operator);
        }
        System.out.println(Arrays.toString(values()));
    }
}
