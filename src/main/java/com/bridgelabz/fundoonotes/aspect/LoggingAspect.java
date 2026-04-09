package com.bridgelabz.fundoonotes.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.bridgelabz.fundoonotes.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Entering: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.bridgelabz.fundoonotes.service..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Exiting: " + joinPoint.getSignature().getName());
    }
}