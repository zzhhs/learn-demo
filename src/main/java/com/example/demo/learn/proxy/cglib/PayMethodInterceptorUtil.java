package com.example.demo.learn.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PayMethodInterceptorUtil {
    public static <T> T createProxy(T target, MethodInterceptor methodInterceptor){
        return (T)Enhancer.create(target.getClass(), methodInterceptor);
    }
}
