package com.example.demo.learn.test;

import java.lang.reflect.Field;

/**
 * Test
 *
 * @author zouzhihao
 * @date 2021/1/24
 */
public class Test {

    static boolean get1(){
        System.out.println("get1");
        return false;
    }

    static boolean get2(){
        System.out.println("get2");
        return true;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Integer i3 = Integer.valueOf(10);
//        Integer i4 = Integer.valueOf(10);
//        Integer i1 = new Integer(10);
//        Integer i2 = new Integer(10);
//        char aa = 1;
//        Character.valueOf(aa);
//        System.out.println(i1 == i2);
//        System.out.println(i2 == i3);
//        System.out.println(i3 == i4);



//        String s1 = String.valueOf("1");
//        String s2 = String.valueOf("1");
//        System.out.println(s1 == s2);
//
//        String s3 = String.valueOf(1);
//        String s4 = String.valueOf(1);
//        System.out.println(s3 == s4);

        String xxx = "123";
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);
        char [] arr  = (char[])value.get(xxx);
        arr[0] = '8';
        System.out.println(xxx);

        int[][] arr3 = new int[1][];


//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("");
//
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("");

    }


    void a(){}
}
