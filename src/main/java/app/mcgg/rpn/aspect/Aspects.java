package app.mcgg.rpn.aspect;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.operator.Operator;
import app.mcgg.rpn.processor.Lookup;
import app.mcgg.rpn.processor.StackProcessor;
import app.mcgg.rpn.util.RPNStack;
import com.sun.javafx.tools.packager.Param;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class Aspects {

    @Autowired
    private StackProcessor stackProcessor;

//    @Autowired
//    private Lookup lookup;

    @Before("execution(* app.mcgg.rpn.operator.*.calculate(..)) && args(a, b,..)")
    public void before(JoinPoint joinPoint, BigDecimal a, BigDecimal b) throws Exception {
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getSimpleName();

//        System.out.println("=============+[before calculate]+===============");
        System.out.println("[Debug] stack: " + stackProcessor.getStackString() + "\t Detail: " +  a + " " + clazzName + " " + b!=null?b:"" );
    }

    @Before("execution(* app.mcgg.rpn.Calculator.*(..)) && args(formula,..)")
    public void beforeEval(JoinPoint joinPoint, String formula) throws Exception {
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getSimpleName();


        System.out.println("=============+[Start Calculating]+===============");
        System.out.println("[Aspect] : " +clazzName + "[stack]: " + stackProcessor.getStackString());
    }

    @Before("execution(* app.mcgg.rpn.processor.StackProcessor.undo(..))")
    public void beforeUndo(JoinPoint joinPoint) throws Exception {
        System.out.println("[Debug] : [stack]: " + stackProcessor.getStackString() + " Undo");
    }

    @Before("execution(* app.mcgg.rpn.processor.StackProcessor.clear(..))")
    public void beforeClear(JoinPoint joinPoint) throws Exception {
        System.out.println("[Debug] : [stack]: " + stackProcessor.getStackString() + "Clear");
    }

    @AfterReturning(pointcut="execution(* app.mcgg.rpn.Calculator.*(..))", returning="returnValue")
    public void after(JoinPoint joinPoint, String returnValue) throws Exception {
        System.out.println("=============+[Calculation Finished]+===============");
        System.out.println("stack " + returnValue);
    }

}