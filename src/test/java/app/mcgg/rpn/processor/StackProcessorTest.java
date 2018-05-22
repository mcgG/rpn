package app.mcgg.rpn.processor;

import app.mcgg.rpn.operator.Add;
import app.mcgg.rpn.operator.Sqrt;
import app.mcgg.rpn.testUtil.Reflection;
import app.mcgg.rpn.util.RPNStack;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.EmptyStackException;

public class StackProcessorTest {

    private StackProcessor processor;
    private RPNStack stack;

    @Before
    public void setup() {
        processor = new StackProcessor();
    }

    @BeforeEach
    public void reset() throws NoSuchFieldException, IllegalAccessException {
        processor = new StackProcessor();
        stack = (RPNStack) Reflection.getField(processor, "stack");
    }

    @Test
    public void testCheckOperandQuantityShouldReturnFalse() throws NoSuchFieldException, IllegalAccessException {
        Reflection.setFieldValue(processor, "stack", new RPNStack());
        assert(!processor.checkOperandQuantity(new Add()));
    }

    @Test
    public void testCheckOperandQuantityShouldReturnTrue() throws NoSuchFieldException, IllegalAccessException {
        RPNStack tmp = new RPNStack();
        tmp.push(new BigDecimal("1.0"));
        tmp.push(new BigDecimal("2.0"));
        Reflection.setFieldValue(processor, "stack", tmp);
        assert(processor.checkOperandQuantity(new Add()));
    }

    @Test
    public void testGetOperandsWithOneOperand() throws NoSuchFieldException, IllegalAccessException {
        RPNStack tmp = new RPNStack();
        tmp.push(new BigDecimal("1.0"));
        tmp.push(new BigDecimal("2.0"));
        Reflection.setFieldValue(processor, "stack", tmp);
        BigDecimal[] rs = processor.getOperands(new Sqrt());
        assert(rs[0].equals(new BigDecimal("2.0")) && rs[1] == null);
    }

    @Test
    public void testGetOperandsWithTwoOperands() throws NoSuchFieldException, IllegalAccessException {
        RPNStack tmp = new RPNStack();
        tmp.push(new BigDecimal("1.0"));
        tmp.push(new BigDecimal("2.0"));
        Reflection.setFieldValue(processor, "stack", tmp);
        BigDecimal[] rs = processor.getOperands(new Add());
        assert(rs[0].equals(new BigDecimal("1.0")) && rs[1].equals(new BigDecimal("2.0")));
    }

    @Test(expected = EmptyStackException.class)
    public void testGetOperandsInvalid() throws NoSuchFieldException, IllegalAccessException {
        Reflection.setFieldValue(processor, "stack", new RPNStack());
        processor.getOperands(new Add());
    }

    @Test
    public void testUndo() throws NoSuchFieldException, IllegalAccessException {
        RPNStack tmp = new RPNStack();
        tmp.push(new BigDecimal("1.0"));
        tmp.push(new BigDecimal("2.0"));
        Reflection.setFieldValue(processor, "stack", tmp);
        processor.undo();
        assert(processor.getStackString().equals("1"));
    }

    @Test(expected = EmptyStackException.class)
    public void testUndoInvalid() throws NoSuchFieldException, IllegalAccessException {
        Reflection.setFieldValue(processor, "stack", new RPNStack());
        processor.undo();
    }

    @Test
    public void testGetSP() throws NoSuchFieldException, IllegalAccessException {
        RPNStack tmp = new RPNStack();
        tmp.push(new BigDecimal("1.0"));
        tmp.push(new BigDecimal("2.0"));
        Reflection.setFieldValue(processor, "stack", tmp);
        processor.undo();
        assert(processor.getSP() == 3);
    }
}
