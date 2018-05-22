package app.mcgg.rpn.util;

import jdk.nashorn.api.tree.NewTree;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class RPNStackTest {

    private RPNStack stack = new RPNStack();

    @BeforeEach
    public void setup() {
        stack = new RPNStack();
    }

    @Test
    public void testClear() {
        stack.push(new BigDecimal(1.0));
        stack.clear();
        assert(stack.isEmpty() == true);
    }

    @Test
    public void testPopWithUndoOperation() throws NoSuchFieldException, IllegalAccessException {

        stack.push(new BigDecimal(1.0));
        stack.pop(true);

        Stack<BigDecimal> privateStack = (Stack<BigDecimal>) getField(stack, "stack").get(stack);
        Stack<BigDecimal> privateHistory = (Stack<BigDecimal>) getField(stack, "history").get(stack);
        assert(privateStack.size() == 0 && privateHistory.size() == 0);
    }

    @Test(expected = EmptyStackException.class)
    public void testPopWithUndoOperationFail() {
        stack.pop(true);
    }

    @Test
    public void testPop() throws NoSuchFieldException, IllegalAccessException {
        stack.push(new BigDecimal(1.0));
        stack.pop(false);

        Stack<BigDecimal> privateStack = (Stack<BigDecimal>) getField(stack, "stack").get(stack);
        Stack<BigDecimal> privateHistory = (Stack<BigDecimal>) getField(stack, "history").get(stack);
        Stack<BigDecimal> shouldBe = new Stack<>();
        shouldBe.add(null);
        shouldBe.add(new BigDecimal(1.0));
        assert(privateStack.size() == 0 && privateHistory.equals(shouldBe));
    }

    @Test(expected = EmptyStackException.class)
    public void testPopFail() {
        stack.pop(true);
    }

    @Test
    public void testGetSp() {
        stack.push(new BigDecimal(1.0));
        stack.push(new BigDecimal(2.0));
        stack.pop(true);
        stack.pop(false);
        assert(stack.getSp() == 4);
    }

    @Test
    public void testIsEmpty() {
        assert(stack.isEmpty() == true);
        stack.push(new BigDecimal(1));
        assert(stack.isEmpty() == false);
    }

    @Test
    public void setSize() {
        assert(stack.size() == 0);
        stack.push(new BigDecimal(1.0));
        assert(stack.size() == 1);
    }

    private Field getField(Object obj, String name) throws NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }
}
