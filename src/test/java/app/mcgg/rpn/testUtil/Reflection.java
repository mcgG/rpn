package app.mcgg.rpn.testUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class Reflection {

    public static Object getField(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    public static Object getFieldObject(Object obj, String name) throws NoSuchFieldException, IllegalAccessException {
        Field field = (Field) getField(obj, name);
        return field.get(obj);
    }

    public static Field setFieldValue(Object obj, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = (Field) getField(obj, name);
        field.set(obj, value);
        return field;
    }

    public static void setMethodAccessible(Object obj, String name) throws NoSuchMethodException {
        Method method = obj.getClass().getDeclaredMethod(name);
        method.setAccessible(true);
    }
}
