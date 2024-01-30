package com.example.lab1.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthControllerAspect {
    @Before("execution(* com.example.lab1.controller.AuthController.*(..))")
    public void log(JoinPoint joinPoint) {
        System.out.println("Advice is triggered!");
        // Implementation to log method call
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("Method " + methodName + " called in " + className);
    }
}
