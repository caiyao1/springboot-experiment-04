package com.example.springbootexperiment04.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@Aspect
public class MyAspect {
    /**
     * 计算所有buy前缀的执行实现
     * @param joinPoint
     * @return
     */
    @Around("execution(* com.example..*.buy*(..))")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start=System.nanoTime();
        Object result=joinPoint.proceed();
        long end=System.nanoTime();
        log.debug("方法：{}()，的执行时间：{}", joinPoint.getSignature().getName(), end - start);
        return result;
    }
    @Around("@within(myInterceptor) || @annotation(myInterceptor)")
    public Object interecptorTarget(ProceedingJoinPoint joinPoint, MyInterceptor myInterceptor) throws Throwable {
        Optional.ofNullable(myInterceptor)
                .or(() -> {
                    MyInterceptor m =
                            joinPoint.getTarget().getClass().getAnnotation(MyInterceptor.class);
                    return Optional.of(m);
                })
                .ifPresent(m -> {
                    for (MyInterceptor.AuthorityType t : m.value()) {
                        log.debug("当前执行方法的权限：{}", t);
                    }
                });
        return joinPoint.proceed();
    }
}
