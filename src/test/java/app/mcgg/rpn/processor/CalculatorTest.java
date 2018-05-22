package app.mcgg.rpn.processor;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.*;
import app.mcgg.rpn.processor.Calculator;
import app.mcgg.rpn.util.Parser;
import app.mcgg.rpn.testUtil.Reflection;
import app.mcgg.rpn.util.BeanCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

public class CalculatorTest {

    private Calculator calculator;
    private Parser parser = new Parser();
    @MockBean
    private BeanCache lookup;


    @Before
    public void setup() {
        calculator = new Calculator();
        lookup = Mockito.mock(BeanCache.class);
        Map<String, Operator> map = new HashMap<>();
        map.put("+", new Add());
        map.put("-", new Subtract());
        map.put("*", new Multiply());
        map.put("/", new Divide());
        map.put("sqrt", new Sqrt());
        map.put("undo", new Undo());
        map.put("clear", new Clear());
        Mockito.when(lookup.getMap()).thenReturn(map);
    }

    @BeforeEach
    public void reset() {
        calculator = new Calculator();
    }

    @Test
    public void testEval() throws CalculatorException, NoSuchMethodException {
//        calculator = Mockito.mock(Calculator.class);
//        Reflection.setMethodAccessible(calculator, "evalElement");
//        Mockito.when(calculator.evalElement("1")).thenReturn(null);
//        String rs = calculator.eval("1");
//        System.out.println(rs);
    }

    @Test
    public void testEvalElementOperand() {}

    @Test
    public void testEvalElementOperator() {}

    @Test
    public void testProcessOperator() {}
}
