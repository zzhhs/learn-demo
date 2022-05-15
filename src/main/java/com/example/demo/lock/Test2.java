package com.example.demo.lock;

import com.example.demo.entity.SysUser;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Test2
 *
 * @author zouzhihao
 * @date 2021/6/10
 */
public class Test2 {
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    int a = 0;

    public void read() {
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println("线程" + name + "读到了a:" + a);
            Thread.sleep(10000);
        } catch (Exception e) {

        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            String name = Thread.currentThread().getName();
            a += 1;
            System.out.println("线程" + name + "改了a:" + a);
            Thread.sleep(10000);
        } catch (Exception e) {

        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test2 test2 = new Test2();

//        Thread thread3 = new Thread(() -> {
//            test2.write();
//        });
//        thread3.setName("C");
//        thread3.start();
//        Thread.sleep(5000);

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                test2.read();
            });
            thread.setName("A" + i);
            thread.start();
        }


//        Thread.sleep(5000);

//        Thread thread4 = new Thread(() -> {
//            test2.write();
//        });
//        thread4.setName("D");
//        thread4.start();

//        Thread thread2 = new Thread(() -> {
//            test2.read();
//        });
//        thread2.setName("B");
//        thread2.start();


    }
}
