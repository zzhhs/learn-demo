package com.example.demo.lock;

import cn.hutool.core.thread.ThreadUtil;
import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 * Test4
 *
 * @author zouzhihao
 * @date 2021/6/11
 */
public class Test4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5,
//                1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(),
//                new DefaultThreadFactory("test"));


        ThreadPoolExecutor poolExecutor2 = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                1L, TimeUnit.MINUTES, new SynchronousQueue<>(),
                new DefaultThreadFactory("test"));
        for (int i = 0; i < 10; i++) {
            Future<?> submit = poolExecutor2.submit(() -> System.out.println(Thread.currentThread().getName()));
            Object o = submit.get();
            System.out.println(o);
        }

//        poolExecutor2.submit(() -> {});
//        ThreadUtil.execute(()->{System.out.println(1);});

//        SynchronousQueue<Integer> objects = new SynchronousQueue<>();
//
//
//
//
//        new Thread(() -> {
//            boolean offer1 = objects.offer(1);
//            try {
//                objects.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(offer1);
//        }).start();

//        new Thread(() -> {
//            boolean offer1 = objects.offer(1);
//            System.out.println(offer1);
//        }).start();

//        new Thread(() -> {
//            try {
//                Integer take = objects.take();
//                System.out.println(take);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

//        Integer take = objects.take();
//        boolean offer2 = objects.offer(2);
//        boolean offer = objects.offer(3);
    }

}
