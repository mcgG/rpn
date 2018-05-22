package app.mcgg.rpn.util;

import app.mcgg.rpn.testUtil.Reflection;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
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
        assert(stack.isEmpty());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testPopWithUndoOperation() throws NoSuchFieldException, IllegalAccessException {

        stack.push(new BigDecimal(1.0));
        stack.pop(true);

        Stack<BigDecimal> privateStack = (Stack<BigDecimal>) Reflection.getFieldObject(stack, "stack");
        Stack<BigDecimal> privateHistory = (Stack<BigDecimal>) Reflection.getFieldObject(stack, "history");
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

        Stack<BigDecimal> privateStack = (Stack<BigDecimal>) Reflection.getFieldObject(stack, "stack");
        Stack<BigDecimal> privateHistory = (Stack<BigDecimal>) Reflection.getFieldObject(stack, "history");
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
        assert(stack.isEmpty());
        stack.push(new BigDecimal(1));
        assert(!stack.isEmpty());
    }

    @Test
    public void setSize() {
        assert(stack.size() == 0);
        stack.push(new BigDecimal(1.0));
        assert(stack.size() == 1);
    }

}
