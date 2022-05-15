package com.example.demo.learn.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AspectTest {

    @Pointcut("execution(* com.example.demo.DemoApplication.wyw(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before......");
    }



    @After("pointcut()")
    public void after() {
        System.out.println("after......");
    }
}



