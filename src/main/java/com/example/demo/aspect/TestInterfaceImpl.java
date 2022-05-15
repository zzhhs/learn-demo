package com.example.demo.aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * TestInterfaceImpl
 *
 * @author zouzhihao
 * @date 2021/6/22
 */
public class TestInterfaceImpl implements TestInterface {

    @Override
    public void a(){
        System.out.println("aaa");
    }
}
class ttt {
    public static void main(String[] args) {
//        TestInterfaceImpl testInterface = new TestInterfaceImpl();
//        testInterface.a();

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
    }
}
