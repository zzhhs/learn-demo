package com.example.demo.learn.proxy.jdk;

import java.lang.reflect.InvocationHandler;

public class ProxyTest {
	public static void main(String[] args) {
//		WechatPay wechatPay = new WechatPay();
//		InvocationHandler invocationHandler = new PayInvocationHandler(wechatPay);
//		Pay pay = PayInvocationHandlerUtil.newProxyInstance(wechatPay, invocationHandler);
//		pay.pay();


		AliPay aliPay = new AliPay();
		InvocationHandler invocationHandlerAli = new PayInvocationHandler(aliPay);
		Pay pay2 = PayInvocationHandlerUtil.newProxyInstance(aliPay, invocationHandlerAli);
		pay2.pay();
		Pay pay3 = aliPay;
		pay3.pay();


//		com.example.demo.learn.proxy.cglib.Pay aliPay = new com.example.demo.learn.proxy.cglib.Pay();
//		InvocationHandler invocationHandlerAli = new PayInvocationHandler(aliPay);
//		com.example.demo.learn.proxy.cglib.Pay pay = PayInvocationHandlerUtil.newProxyInstance(aliPay, invocationHandlerAli);
//		pay.pay();
	}
}
