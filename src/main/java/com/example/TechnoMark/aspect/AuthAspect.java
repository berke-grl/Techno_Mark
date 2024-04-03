package com.example.TechnoMark.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AuthAspect {
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(com.example.TechnoMark.model.AuthResponse com.example.TechnoMark.service.AuthenticationService.login(*))")
    public void forLoginMethod() {
    }

    @Pointcut("execution(com.example.TechnoMark.model.AuthResponse com.example.TechnoMark.service.AuthenticationService.register(*))")
    public void forRegisterMethod() {
    }

    @Pointcut("execution(String com.example.TechnoMark.service.AuthenticationService.getCurrentUsername())")
    public void forGetCurrentUsernameMethod() {
    }

    @Pointcut("forLoginMethod() || forRegisterMethod() || forGetCurrentUsernameMethod()")
    public void forAnyAuthMethod() {
    }

    @Before("forAnyAuthMethod()")
    public void before(JoinPoint joinPoint) {
        //Display method we are calling
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("==> in @Before calling method : " + methodName);

        //Display the arguments to the method
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("==> argument :" + arg);
        }
    }

    @AfterReturning(pointcut = "forAnyAuthMethod()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        //Display method we are returning from
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("==> in @After calling method : " + methodName);

        //Display data returned
        logger.info("==> result :" + result);
    }
}
