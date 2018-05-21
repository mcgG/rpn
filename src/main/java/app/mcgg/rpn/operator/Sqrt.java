package app.mcgg.rpn.operator;

import app.mcgg.rpn.exception.CalculatorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

@Service("sqrt")
public class Sqrt implements Operator {

    @Value("${decimal.precision}")
    private int precision;

    private int requiredOperandsNumber = 1;

    @Override
    public int getRequiredOperandsNumber() {
        return requiredOperandsNumber;
    }

    @Override
    public BigDecimal calculate(BigDecimal x, BigDecimal y) {
        BigDecimal num = x == null ? y : x;
        if (num.compareTo(BigDecimal.ZERO) < 0) {
            throw new ArithmeticException("Cannot square root negative number!");
        }
//
//        String numStr = num.toString();
//        int decimalPrecision = 0;
//        if (numStr.contains(".")) {
//            decimalPrecision = numStr.split("\\.")[0].length();
//        } else {
//            decimalPrecision = numStr.length();
//        }
        return num.sqrt(new MathContext(precision));
    }
}
