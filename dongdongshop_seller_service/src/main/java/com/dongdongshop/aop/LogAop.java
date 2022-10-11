package com.dongdongshop.aop;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LogAop {

    @Pointcut("execution(* com.dongdongshop.service.*.*(..)) || @annotation(com.dongdongshop.annotation.DKlog)")
    public void pointCutMethod() {
    }

    @After("pointCutMethod()")
    public void after(JoinPoint joinPoint) throws Throwable{
        log.info("当前参数为:{} 当前方法名为:{}",joinPoint.getArgs(),joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "pointCutMethod()",returning = "obj")
    public void afterReturning(Object obj){
        log.info("当前方法返回值为:{}", JSONUtil.toJsonStr(obj));
    }


}
