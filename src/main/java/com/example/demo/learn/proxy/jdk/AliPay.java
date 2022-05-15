package com.example.demo.learn.proxy.jdk;

public class AliPay implements Pay {
	@Override
	public void pay() {
		System.out.println("支付宝支付！！！JDK");
	}
}
