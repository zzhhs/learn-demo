package com.example.demo.lock;

import lombok.Synchronized;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Test
 *
 * @author zouzhihao
 * @date 2021/6/8
 */
public class Test {
    ReentrantLock reentrantLock = new ReentrantLock();

    Condition condition = reentrantLock.newCondition();

    public void go() {
        reentrantLock.lock();
        try {
            System.out.println("123");
            try {
                Thread.sleep(1000011000);
//                go2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public void go2(){
        reentrantLock.lock();
        try {
            System.out.println("123666");
        } finally {
            reentrantLock.unlock();
        }
    }

    public void tryLock() throws InterruptedException {
        reentrantLock.tryLock(2, TimeUnit.MINUTES);
        try {
            System.out.println("123666");
        } finally {
            reentrantLock.unlock();
        }
    }

    public void await() {
        reentrantLock.lock();
        try {
            System.out.println("阻塞了");
            condition.await();
            System.out.println("唤醒了");
        }catch (Exception e){
        }finally {
            reentrantLock.unlock();
        }
    }

    public void signal() {
        reentrantLock.lock();
        try {
            System.out.println("唤醒它======================》》");
            condition.signal();
        }catch (Exception e){
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
//        Thread thread1 = new Thread(() -> {
//            try {
//                test.go();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread1.setName("A");
//        thread1.start();
//        Thread thread2 = new Thread(() -> {
//            try {
//                test.tryLock();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread2.setName("B");
//        thread2.start();



        Thread thread4 = new Thread(() -> {
            test.await();
        });
        thread4.setName("D");
        thread4.start();

        Thread.sleep(5000);

        Thread thread3 = new Thread(() -> {
            test.go();
        });
        thread3.setName("C");
//        thread3.start();

        Thread.sleep(5000);

//        Thread thread5 = new Thread(() -> {
//            test.signal();
//        });
//        thread5.setName("E");
//        thread5.start();
    }
}
