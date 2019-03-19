package com.gaidukevich.tragent.repository.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RepositoryLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryLog.class);

    @Pointcut("execution(* com.gaidukevich.tragent.repository.*.*(..))")
    public void doSomething() {
    }

    @AfterReturning(value = "doSomething()", argNames = "joinPoint")
    public void beforeCall(JoinPoint joinPoint) {
        String className = joinPoint.getThis().toString().split("@")[0];
        String methodName = joinPoint.getSignature().getName();

        LOGGER.info("Class :" + className + " | Method :" + methodName + "() with Args => "
                + Arrays.asList(joinPoint.getArgs()) + " worked successfully.");
    }

    @Before(value = "doSomething()", argNames = "joinPoint")
    public void isRunningGetByIdJdbc(JoinPoint joinPoint) {
        String className = joinPoint.getThis().toString().split("@")[0];
        String methodName = joinPoint.getSignature().getName();

        LOGGER.info("Class :" + className + " | Method :" + methodName + "(..) will be called.");
    }
}
