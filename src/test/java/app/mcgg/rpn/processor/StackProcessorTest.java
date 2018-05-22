package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Add;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.operator.Sqrt;
import app.mcgg.rpn.testUtil.Reflection;
import app.mcgg.rpn.util.RPNStack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.Verifier;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EmptyStackException;

public class StackProcessorTest {

    private StackProcessor processor;

    @MockBean
    private RPNStack stack;
    @MockBean
    private Operator op;

    @Before
    public void setup() {
        op = Mockito.mock(Operator.class);
        stack = Mockito.mock(RPNStack.class);
        processor = new StackProcessor(stack);
    }

    @Test
    public void testGetOperandsWithOneOperand(){
        Mockito.when(op.getRequiredOperandsNumber()).thenReturn(1);
        Mockito.when(stack.pop(false)).thenReturn(new BigDecimal(1));
        BigDecimal[] rs = processor.getOperands(new Sqrt());
        Assert.assertArrayEquals(rs, new BigDecimal[]{new BigDecimal(1), null});
    }

    @Test
    public void testGetOperandsWithTwoOperands() {
        Mockito.when(op.getRequiredOperandsNumber()).thenReturn(2);
        Mockito.when(stack.pop(false)).thenReturn(new BigDecimal(1));
        BigDecimal[] rs = processor.getOperands(new Add());
        Assert.assertArrayEquals(rs, new BigDecimal[]{new BigDecimal(1), new BigDecimal(1)});
    }

    @Test(expected = EmptyStackException.class)
    public void testGetOperandsInvalid() {
        Mockito.when(stack.pop(false)).thenThrow(new EmptyStackException());
        processor.getOperands(new Add());
    }
}
