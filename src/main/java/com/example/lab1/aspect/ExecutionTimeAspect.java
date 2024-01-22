package com.example.lab1.aspect;

import com.example.lab1.model.ExceptionLog;
import com.example.lab1.model.Logger;
import com.example.lab1.repository.ExceptionLogRepository;
import com.example.lab1.repository.LoggerRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Aspect
@Configuration
public class ExecutionTimeAspect {

    @Autowired
    LoggerRepository loggerRepository;

    @Autowired
    ExceptionLogRepository exceptionLogRepository;

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExecutionTime {
    }

    @Around("@annotation(com.example.lab1.aspect.ExecutionTimeAspect.ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {

            Object result = joinPoint.proceed();

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            logExecution(joinPoint, executionTime);

            return result;
        } catch (Throwable throwable) {

            logException(joinPoint, throwable);
            throw throwable;
        }
    }

    private void logExecution(JoinPoint joinPoint, long executionTime) {

        String methodName = joinPoint.getSignature().getName();
        String principle = "fake_static_user";
        String operationLog = String.format("Method: %s, Execution Time: %d ms", methodName, executionTime);

        Logger logEntry = new Logger();
        logEntry.setTransactionId(generateTransactionId());
        logEntry.setLogDate(Date.valueOf(LocalDate.now()));
        logEntry.setLogTime(Date.valueOf(LocalDate.now()));
        logEntry.setPrinciple(principle);
        logEntry.setOperation(operationLog);

        loggerRepository.save(logEntry);
    }

    private void logException(JoinPoint joinPoint, Throwable exception) {

        String methodName = joinPoint.getSignature().getName();
        String principle = "fake_static_user"; // Fake static user for now

        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setTransactionId(generateTransactionId());
        exceptionLog.setDate(LocalDate.now());
        exceptionLog.setTime(LocalTime.now());
        exceptionLog.setPrinciple(principle);
        exceptionLog.setOperation("Method: " + methodName);
        exceptionLog.setExceptionType(exception.getClass().getName());

        exceptionLogRepository.save(exceptionLog);
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
