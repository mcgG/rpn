package app.mcgg.rpn.util;

import app.mcgg.rpn.operator.Add;
import app.mcgg.rpn.operator.Clear;
import app.mcgg.rpn.operator.Subtract;
import app.mcgg.rpn.operator.Undo;
import app.mcgg.rpn.testUtil.Reflection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
class LookupTest {

    @Mock
    private BeanCache lookup;
    private String[] operand;
    private String[] stack;


    @Mock
    private ApplicationContextProvider applicationContextProvider;

    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(applicationContextProvider.getBeanByName("+")).thenReturn(new Add());
        Mockito.when(applicationContextProvider.getBeanByName("-")).thenReturn(new Subtract());
        Mockito.when(applicationContextProvider.getBeanByName("undo")).thenReturn(new Undo());
        Mockito.when(applicationContextProvider.getBeanByName("clear")).thenReturn(new Clear());
        lookup = new BeanCache();
        Reflection.setFieldValue(lookup, "operand", new String[]{"+", "-"});
        Reflection.setFieldValue(lookup, "stack", new String[]{"undo", "clear"});
        Reflection.setFieldValue(lookup, "applicationContextProvider", applicationContextProvider);
        operand = (String[]) Reflection.getFieldObject(lookup, "operand");
        stack = (String[]) Reflection.getFieldObject(lookup, "stack");
    }

    @Test
    void testGetMap() {
        lookup.getMap();
        assert(operand.length == 2 && stack.length == 2);
    }
}