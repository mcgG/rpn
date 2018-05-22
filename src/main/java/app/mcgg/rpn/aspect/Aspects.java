package app.mcgg.rpn.aspect;

import app.mcgg.rpn.processor.StackProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Aspect
public class Aspects {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StackProcessor stackProcessor;

    @Before("execution(* app.mcgg.rpn.processor.Calculator.*(..)) && args(formula,..)")
    public void beforeCalculation(JoinPoint joinPoint, String formula) throws Exception {
        logger.info("=============+[Start Calculating]+===============");
        logger.info("stack: [" + stackProcessor.getStackString()+"]");
        logger.info("stack: [%s]");
    }

    @After("execution(* app.mcgg.rpn.operator.*.calculate(..)) && args(x, y,..)")
    public void beforeOperation(JoinPoint joinPoint, BigDecimal x, BigDecimal y) throws Exception {
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getSimpleName();
        logger.info("stack: [" + stackProcessor.getStackString() + "]\t Detail: " + x + " " + clazzName + " " + (y!=null?y:"") );
    }

    @Before("execution(* app.mcgg.rpn.processor.StackProcessor.undo(..))")
    public void beforeUndo(JoinPoint joinPoint) {
        logger.info("stack: " + stackProcessor.getStackString() + " Undo");
    }

    @Before("execution(* app.mcgg.rpn.processor.StackProcessor.clear(..))")
    public void beforeClear(JoinPoint joinPoint) {
        logger.info("stack: [" + stackProcessor.getStackString() + "] Clear");
    }

    @After("execution(* app.mcgg.rpn.processor.StackProcessor.push(..)) && args(n,..)")
    public void beforePush(JoinPoint joinPoint, BigDecimal n) {
        logger.info("stack: [" + stackProcessor.getStackString() + "] Push");
    }

    @AfterReturning(pointcut="execution(* app.mcgg.rpn.processor.Calculator.*(..))", returning="returnValue")
    public void afterCalculation(JoinPoint joinPoint, String returnValue) throws Exception {
        logger.info("result stack: [" + returnValue + "]");
        logger.info("=============+[Calculation Finished]+===============");
        System.out.println("stack: " + returnValue);
    }

    @AfterThrowing(pointcut="execution(* app.mcgg.rpn.processor.Calculator.eval(..))", throwing="e")
    public void afterCalculationThrowing(JoinPoint joinPoint, Exception e) {
        logger.warn(e.getMessage());
        logger.warn("stack: [" + stackProcessor.getStackString() + "]");
        logger.warn("=============+[Calculation Finished with error]+===============");
        System.out.println(e.getMessage());
        System.out.println("stack: " + stackProcessor.getStackString());
    }

}