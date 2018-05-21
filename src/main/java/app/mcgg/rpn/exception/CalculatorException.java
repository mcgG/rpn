package app.mcgg.rpn.exception;

public class CalculatorException extends Exception {
    public CalculatorException(String operator, int sp, String msg) {
        super(String.format("operator %s (position: %d): %s", operator, sp, msg));
    }
}
