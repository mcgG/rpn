package app.mcgg.rpn.Test;

import java.math.BigDecimal;
import java.math.MathContext;

public class Test {

    public static void main(String[] args) {

        BigDecimal bb = new BigDecimal("-");

        MathContext mc = new MathContext(16);
        BigDecimal a = new BigDecimal(1.21);
        System.out.println(a);
        System.out.println(a.sqrt(mc));


        String s = "1.20999999999999996447286321199499070644378662109375";
        String s2 = "1.20";
        System.out.println(s2.split("\\.").length);
    }


}
