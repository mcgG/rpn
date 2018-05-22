package app.mcgg.rpn.util;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Parser {

    public BigDecimal parseDouble(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
