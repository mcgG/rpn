package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.*;
import app.mcgg.rpn.util.BeanCache;
import app.mcgg.rpn.util.Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class FormulaProcessorTest {

    private FormulaProcessor processor;

    @MockBean
    private Parser parser;
    @MockBean
    private BeanCache lookup;
    @MockBean
    private StackProcessor stackProcessor;

    @Before
    public void setup() {
        parser = Mockito.mock(Parser.class);
        lookup = Mockito.mock(BeanCache.class);
        stackProcessor = Mockito.mock(StackProcessor.class);
        processor = new FormulaProcessor(parser, lookup, stackProcessor);
    }

    @Test
    public void testProcessElement() throws CalculatorException {
        Mockito.when(parser.parseDouble("1")).thenReturn(new BigDecimal("1"));
        Assert.assertEquals(processor.processElement("1"), new BigDecimal("1"));
    }

    @Test
    public void testProcessElementShouldReturnNull() throws CalculatorException {
        Mockito.when(parser.parseDouble("+")).thenReturn(null);
        Assert.assertEquals(processor.processElement("+"), null);
    }

    @Test(expected = CalculatorException.class)
    public void testProcessOperatorNull() throws CalculatorException {
        Map<String, Operator> map = new HashMap<>();
        map.put("+", new Add());
        Mockito.when(lookup.getMap()).thenReturn(map);
        processor.processOperator("&");
    }

    @Test
    public void testProcessOperatorUndo() throws CalculatorException {
        Map<String, Operator> map = new HashMap<>();
        map.put("undo", new Undo());
        Mockito.when(lookup.getMap()).thenReturn(map);
        Assert.assertEquals(processor.processOperator("undo"), null);
    }

    @Test
    public void testProcessOperatorClear() throws CalculatorException {
        Map<String, Operator> map = new HashMap<>();
        map.put("clear", new Clear());
        Mockito.when(lookup.getMap()).thenReturn(map);
        Assert.assertEquals(processor.processOperator("clear"), null);
    }

    @Test(expected = CalculatorException.class)
    public void testProcessOperatorThrowException() throws CalculatorException {
        Map<String, Operator> map = new HashMap<>();
        map.put("+", new Add());
        Mockito.when(lookup.getMap()).thenReturn(map);
        Mockito.when(stackProcessor.checkOperandQuantity(Mockito.any())).thenReturn(false);
        processor.processOperator("+");
    }

    @Test
    public void testProcessOperatorShouldReturnOperator() throws CalculatorException {
        Map<String, Operator> map = new HashMap<>();
        map.put("+", new Add());
        Mockito.when(lookup.getMap()).thenReturn(map);
        Mockito.when(stackProcessor.checkOperandQuantity(Mockito.any())).thenReturn(true);
        Assert.assertEquals(processor.processOperator("+"), "+");
    }

    @Test
    public void testProcessCalculation() throws CalculatorException {
        Map<String, Operator> map = new HashMap<>();
        map.put("+", new Add());
        Mockito.when(lookup.getMap()).thenReturn(map);
        Mockito.when(stackProcessor.getOperands(Mockito.any())).
                thenReturn(new BigDecimal[]{new BigDecimal("1"), new BigDecimal("2")});
        Assert.assertEquals(processor.processCalculation("+"), new BigDecimal("3"));
    }

    @Test(expected = CalculatorException.class)
    public void testProcessCalculationThrowException() throws CalculatorException {
        Map<String, Operator> map = new HashMap<>();
        map.put("/", new Divide());
        Mockito.when(lookup.getMap()).thenReturn(map);
        Mockito.when(stackProcessor.getOperands(Mockito.any())).
                thenReturn(new BigDecimal[]{new BigDecimal("1"), new BigDecimal("0")});
        processor.processCalculation("/");
    }
}
