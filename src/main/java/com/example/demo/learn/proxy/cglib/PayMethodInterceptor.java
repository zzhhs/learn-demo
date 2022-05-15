package com.example.demo.learn.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PayMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        startPay();
        Object result = methodProxy.invokeSuper(o, objects);
        endPay();
        return result;
    }

    public void startPay(){
        System.out.println("开始支付");
    }

    public void endPay(){
        System.out.println("支付完成");
    }
}
