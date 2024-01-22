package com.example.lab1.aspect;

import com.example.lab1.repository.LoggerRepository;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggerAspect {

    @Autowired
    LoggerRepository loggerRepository;
}
