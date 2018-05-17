package app.mcgg.rpn;

import app.mcgg.rpn.factory.MyStack;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack.toString());


    }
}
