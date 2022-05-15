package com.example.demo.learn.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class PayInvocationHandlerUtil {

	public static <T> T newProxyInstance(T targetObject, InvocationHandler invocationHandler){
		ClassLoader classLoader = targetObject.getClass().getClassLoader();
		Class<?>[] interfaces = targetObject.getClass().getInterfaces();
		return (T)Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
	}



//	Object targetObject;
//
//	public PayInvocationHandlerUtil(Object targetObject){
//		this.targetObject = targetObject;
//	}
//
//
//	@Override
//	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		startPay();
//		proxy = method.invoke(targetObject, args);
//		endPay();
//		return proxy;
//	}
//
//	public void startPay(){
//		System.out.println("开始支付");
//	}
//
//	public void endPay(){
//		System.out.println("支付完成");
//	}
}
