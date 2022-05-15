package com.example.demo.learn.proxy.cglib;

public class PayTest {
    public static void main(String[] args) {
        Pay pay = new Pay();
        PayMethodInterceptor payMethodInterceptor = new PayMethodInterceptor();
        Pay proxy = PayMethodInterceptorUtil.createProxy(pay, payMethodInterceptor);
        proxy.pay();
    }
}
