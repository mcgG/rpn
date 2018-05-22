package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.*;
import app.mcgg.rpn.processor.Calculator;
import app.mcgg.rpn.util.Parser;
import app.mcgg.rpn.testUtil.Reflection;
import app.mcgg.rpn.util.BeanCache;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class CalculatorTest {

    private Calculator calculator;

    @MockBean
    private FormulaProcessor formulaProcessor;

    @Before
    public void setup() {
        formulaProcessor = Mockito.mock(FormulaProcessor.class);
        calculator = new Calculator(formulaProcessor);
    }

    @Test
    public void testEval() throws CalculatorException {
        Mockito.when(formulaProcessor.processElement("+")).thenReturn(null);
        Mockito.when(formulaProcessor.processElement("1")).thenReturn(new BigDecimal(1));
        Mockito.when(formulaProcessor.processOperator("+")).thenReturn("+");
        Mockito.when(formulaProcessor.processOperator("undo")).thenReturn(null);
        Mockito.when(formulaProcessor.processOperator("clear")).thenReturn(null);
        Mockito.when(formulaProcessor.getStack()).thenReturn("");
        Assert.assertEquals(calculator.eval("1 2 + undo clear"), "");
    }

    @Test(expected = CalculatorException.class)
    public void testEvalWithCalculatorException() throws CalculatorException {
        Mockito.when(formulaProcessor.processElement("x")).thenThrow(new CalculatorException("x", 2, "invalid operator"));
        calculator.eval("1 2 x");
    }


}
