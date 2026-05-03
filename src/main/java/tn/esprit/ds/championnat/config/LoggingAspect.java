package tn.esprit.ds.championnat.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* tn.esprit.ds.championnat.services.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info(">> Entering: {} with args={}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @After("execution(* tn.esprit.ds.championnat.services.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("<< Exiting: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "execution(* tn.esprit.ds.championnat.services.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("-- Returned from: {} result={}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "execution(* tn.esprit.ds.championnat.services.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("!! Exception in: {} message={}", joinPoint.getSignature().toShortString(), ex.getMessage(), ex);
    }
}