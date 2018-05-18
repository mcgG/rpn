package app.mcgg.rpn.Test.factory;

import java.util.Arrays;
import java.util.Stack;

/*
9 7 8 1 2  +
[2, 1]
{9 7 8 3}
{9 7 8 1 2}

 */

public class MyStack extends Stack<String> {

    Stack<String> cache = new Stack<>();
    Stack<String> operatorStack = new Stack<>();

    public MyStack() {
    }


    public void undo() {
        pop();
    }

    @Override
    public String push(String element) {
        addElement(element);
        cache.push(element);
        return element;
    }

    @Override
    public String pop() {
        String element;
        int len = size();
        element = peek();
        removeElementAt(len - 1);
        cache.push(element);
        return element;
    }

    private static Object[] subArray(Object[] src, int begin, int count) {
        Object[] arr = new Object[count];
        System.arraycopy(src, begin, arr, 0, count);
        return arr;
    }

    public String toString() {
        return Arrays.toString(subArray(elementData, 0, elementCount));
    }


}
