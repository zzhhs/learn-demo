package com.example.demo.learn.test;

/**
 * Test3
 *
 * @author zouzhihao
 * @date 2021/1/26
 */
public abstract class Test3 {

    public String a = "";

    public void ab(){

    }

    public abstract String b();
}
class Test4 extends Test3{

    public String a = "";



    @Override
    public String b() {

        String a = super.a;
        super.a += "12";
        String a1 = a;
        return null;
    }
}