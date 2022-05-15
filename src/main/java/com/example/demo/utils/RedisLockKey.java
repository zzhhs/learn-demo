package com.example.demo.utils;

public class RedisLockKey {

    public static String getLockKey(){
        return String.valueOf(System.currentTimeMillis());
    }
}
