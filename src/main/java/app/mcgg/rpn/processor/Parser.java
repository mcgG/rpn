package app.mcgg.rpn.processor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Parser {

    public static BigDecimal parseDouble(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
