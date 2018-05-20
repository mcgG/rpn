package app.mcgg.rpn.aspect;

import app.mcgg.rpn.util.RPNStack;
import com.sun.javafx.tools.packager.Param;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.MethodInfo;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class Aspects {

    @Autowired
    private RPNStack stack;

//    @Before(value = "app.mcgg.rpn.aspect.PointCuts.aopDemo()")
//    @Before("execution(public double app.mcgg.rpn.operator.*.*(Double, Double))")
    public void before(JoinPoint joinPoint) throws Exception{
        Object[] obj = joinPoint.getArgs();

        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName(); //获取方法名称
        //获取参数名称和值
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();

        System.out.println("=============+++++++++++++===============");
        System.out.println(stack.toString());
        System.out.println(clazzName + " " + methodName + " [Aspect1] before advise " + obj.length + " stack: " + stack.size());
    }

}