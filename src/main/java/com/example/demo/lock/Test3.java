package com.example.demo.lock;

import cn.hutool.core.thread.ThreadUtil;
import com.example.demo.entity.SysUser;
import jodd.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.*;
import java.util.concurrent.*;

/**
 * Test3
 *
 * @author zouzhihao
 * @date 2021/6/10
 */
public class Test3 {


    BasicThreadFactory namedThreadFactory = new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build();

    ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 200,
                                          1L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public void threadPool(){
        pool.execute(()-> System.out.println(Thread.currentThread().getName()));
    }

    public void arrayBlockingQueue() throws InterruptedException {
//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue();
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        new Thread(() -> {
            try {
                arrayBlockingQueue.put(1);
                arrayBlockingQueue.put(2);
                arrayBlockingQueue.put(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                Integer poll = arrayBlockingQueue.poll(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        arrayBlockingQueue.take();
//        arrayBlockingQueue.offer(1);
//        arrayBlockingQueue.add(1);
//        arrayBlockingQueue.poll();
//        arrayBlockingQueue.
    }

    public void linkedBlockingDeque() throws InterruptedException {
        LinkedBlockingQueue<Integer> linkedBlockingDeque = new LinkedBlockingQueue<>(2);
        linkedBlockingDeque.offer(1);
        linkedBlockingDeque.offer(2);
        linkedBlockingDeque.offer(3);

        linkedBlockingDeque.poll(1, TimeUnit.MINUTES);
        linkedBlockingDeque.take();
    }


    public void synchronousQueue() throws InterruptedException {
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<Integer>();

        new Thread(() -> {
            try {
                System.out.println(synchronousQueue.poll(1L, TimeUnit.MINUTES));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(50000);

        new Thread(() -> {
            try {
                synchronousQueue.offer(1, 1L, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void countDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("倒计时"+ finalI);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }).start();
        }


//        new Thread(() -> {
//            System.out.println("倒计时2");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            countDownLatch.countDown();
//        }).start();
//
//        new Thread(() -> {
//            System.out.println("倒计时3");
//            countDownLatch.countDown();
//        }).start();
        countDownLatch.await();
        System.out.println("执行完成");
    }


    int x = 0;

    void get(){
        System.out.println(x);
    }

    synchronized void write() throws InterruptedException {
        x = 1;
        System.out.println("修改了====================》》");
        Thread.sleep(10000);
        System.out.println("释放了====================》》");
    }



    public static void main(String[] args) throws InterruptedException {
        Test3 test3 = new Test3();
//        test3.arrayBlockingQueue();
//        test3.linkedBlockingDeque();
//        test3.synchronousQueue();
//        test3.countDownLatch();
//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                test3.get();;
//            }).start();
//        }
//        new Thread(() -> {
//            try {
//                test3.write();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ;
//        }).start();
//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                test3.get();;
//            }).start();
//        }

        test3.threadPool();
    }
}class Test5{

    Semaphore semaphore = new Semaphore(5);

    void acquire() throws InterruptedException {
        semaphore.acquire();
        System.out.println("获取信号成功");
    }

    void release() throws InterruptedException {
        semaphore.release();
//        System.out.println("释放信号成功");
    }

    public static void main(String[] args) {
        Test5 test5 = new Test5();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    test5.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        new Thread(() -> {
            try {
                test5.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

//        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
//        map.put("1", "2");

    }
}
class A{

    static void a (){
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Runnable a = () -> {
                synchronized (A.class){
                    try {
                        Thread.sleep(5000);
                        System.out.println(finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            threads.add(new Thread(a));
        }

        threads.forEach(o -> o.start());
        threads.forEach(o -> {
            try {
                o.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        A.a();
    }
//        List<Test3> arrayList = new ArrayList<>();
//        int i = 0;
//        while (true){
//            Test3 test3 = new Test3();
//            arrayList.add(test3);
//            System.out.println(i);
//            i++;
//        }


}