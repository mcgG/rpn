package app.mcgg.rpn.Test;

import javax.sound.midi.Soundbank;
import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Test {
    private static Stack<Double> stack = new Stack<>();
    private static Stack<Double> cache = new Stack<>();

    static String[] items = "1 2 3 + sqrt 5 + 1 2 3 undo undo undo undo undo".split("\\s");

    private static Double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return null;
        }
    }

    private static void printStack(Stack<Double> s) {
        Stack<Double> t = (Stack<Double>) s.clone();
        Stack<Double> tmp = new Stack<>();
        while (!t.isEmpty()) {
            tmp.push(t.pop());
        }
        while (!tmp.isEmpty()) {
            System.out.print(tmp.pop() + ", ");
        }
        System.out.println();

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(items));
        for (String item : items) {
            Double num = parseDouble(item);
            if (num == null) {
                if (item.equals("+")) {
                    Double second = popOperand(false);
                    Double first = popOperand(false);
                    pushOperand(second + first);
                    printStack(stack);
                } else if (item.equals("sqrt")) {
                    Double first = popOperand(false);
                    pushOperand(Math.sqrt(first));
                    printStack(stack);
                } else if (item.equals("undo")) {
                    reverse();
                    printStack(stack);
                }
            } else {
                pushOperand(num);
                printStack(stack);
            }
        }
        System.out.println("==========================");
        printStack(stack);
    }

    private static void reverse() {
        if (cache.peek() == null) {
            cache.pop();
            stack.pop();
            while (cache.peek() != null) {
                Double tmp = cache.pop();
                stack.push(tmp);
            }
        } else {
            System.out.println("error");
        }
    }

    private static void pushOperand(Double op) {
        stack.push(op);
        cache.push(null);
    }

    private static double popOperand(boolean isUndo) {
        Double tmp = stack.pop();
        if (!isUndo) {
            cache.push(tmp);
        }
        return tmp;
    }

}
