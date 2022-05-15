package com.example.demo.learn.proxy.jdk;

public class WechatPay implements Pay {
	@Override
	public void pay() {
		System.out.println("微信支付！！！JDK");
	}
}
