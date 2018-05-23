package app.mcgg.rpn.aspect;

import app.mcgg.rpn.exception.CalculatorException;
import app.mcgg.rpn.processor.StackProcessor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Aspect
public class Aspects {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private StackProcessor stackProcessor;

    public Aspects(StackProcessor stackProcessor) {
        this.stackProcessor = stackProcessor;
    }

    /**
     *
     * @param joinPoint
     * @param formula
     * @throws Exception
     *
     * Log calculation start point
     */
    @Before("execution(* app.mcgg.rpn.processor.Calculator.eval(..)) && args(formula,..)")
    public void beforeCalculation(JoinPoint joinPoint, String formula) throws Exception {
        logger.info("=============+[Start Calculating]+===============");
        logger.info(String.format("stack: [%s]", stackProcessor.getStackString()));
    }

    /**
     *
     * @param joinPoint
     * @param x
     * @param y
     * @throws Exception
     *
     *  Log all operation steps
     */
    @After("execution(* app.mcgg.rpn.operator.*.calculate(..)) && args(x, y,..)")
    public void afterOperation(JoinPoint joinPoint, BigDecimal x, BigDecimal y) throws Exception {
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getSimpleName();
        logger.info(String.format("stack: [%s] Detail: %s %s %s", stackProcessor.getStackString(), x, clazzName, (y!=null?y:"")));
    }

    /**
     *
     * @param joinPoint
     *
     *  Log all undo operation
     */
    @After("execution(* app.mcgg.rpn.processor.StackProcessor.undo(..))")
    public void beforeUndo(JoinPoint joinPoint) {
        logger.info(String.format("stack: [%s] Detail: Undo", stackProcessor.getStackString()));
    }

    /**
     *
     * @param joinPoint
     *
     *  Log all clear operation
     */
    @After("execution(* app.mcgg.rpn.processor.StackProcessor.clear(..))")
    public void beforeClear(JoinPoint joinPoint) {
        logger.info(String.format("stack: [%s] Detail: Clear", stackProcessor.getStackString()));
    }

    /**
     *
     * @param joinPoint
     * @param n
     *
     * Log all push stack operation
     */
    @After("execution(* app.mcgg.rpn.processor.StackProcessor.push(..)) && args(n,..)")
    public void beforePush(JoinPoint joinPoint, BigDecimal n) {
        logger.info(String.format("stack: [%s] Detail: Push", stackProcessor.getStackString()));
    }

    /**
     *
     * @param joinPoint
     * @param returnValue
     *
     *  Print result stack value
     */
    @AfterReturning(pointcut="execution(* app.mcgg.rpn.processor.Calculator.eval(..))", returning="returnValue")
    public void afterCalculation(JoinPoint joinPoint, String returnValue) {
        logger.info(String.format("result stack: [%s]", returnValue));
        logger.info("=============+[Calculation Finished]+===============");
        System.out.println("stack: " + returnValue);
    }

    /**
     *
     * @param joinPoint
     * @param e
     *
     *  Handle all calculation exceptions
     */
    @AfterThrowing(pointcut="execution(* app.mcgg.rpn.processor.Calculator.eval(..))", throwing="e")
    public void afterCalculationThrowing(JoinPoint joinPoint, CalculatorException e) {
        logger.warn(e.getMessage());
        logger.warn(String.format("stack: [%s]", stackProcessor.getStackString()));
        logger.warn("=============+[Calculation Finished with error]+===============");
        System.out.println(e.getMessage());
        System.out.println("stack: " + stackProcessor.getStackString());
    }

}