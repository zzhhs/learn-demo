package com.example.demo.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class RedisLockAspect {
    @Autowired
    RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.example.demo.aspect.RedisLock)")
    private void pointcut() {
    }

    @Before("pointcut()&&@annotation(redisLock)")
    public void before(JoinPoint joinPoint, RedisLock redisLock) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //加锁



        log.info("前置"+request.getParameter("id"));
    }

    @After("pointcut()&&@annotation(redisLock)")
    public void after(JoinPoint joinPoint, RedisLock redisLock) {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        //解锁
        log.info("最终"+request.getParameter("id"));
    }
}
