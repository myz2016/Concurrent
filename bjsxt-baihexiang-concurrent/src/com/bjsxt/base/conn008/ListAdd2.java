package com.bjsxt.base.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ListAdd2 {
    /*volatile*/
    private volatile static List list = new ArrayList();

    public void add() {
        list.add("bjsxt");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final Object lock = new Object();
        final ListAdd2 list1 = new ListAdd2();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
//                        将synchronized块写在for循环里面
//                        synchronized (lock) {
                            list1.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素...");
                            Thread.sleep(500);
                            if (list1.size() == 5) {
                                System.out.println("发出通知....");
//                              lock.notify();
//                              lock.wait();
                                countDownLatch.countDown();
                            }
//                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    synchronized (lock) {
                        if (list1.size() != 5) {
                            System.out.println(Thread.currentThread().getName() + "进入wait状态....");
//                            lock.wait();
                            countDownLatch.await();
                        }
                        System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + ", list size = 5 线程停止...");
                        /**
                         * 这里如果打印出List1的size值，也不会是等于5的，因为t1线程发出通知以后，t2线程并不会马上醒过来，因为notify不会释放锁，
                         * 所以t2线程是获取不到锁的，所以不能执行，只有当t1线程执行完了以后，t2线程才能获取到锁，然后继续执行。如果t1释放锁以后，
                         * 存在着不止t2一个线程的话，比如还有t3,t4线程，这时t2,t3,t4会竞争锁，谁抢到了谁就可以执行。注意，他们竞争同一把锁的前
                         * 提是，他们都是用的同一个锁对象，比如这里的lock。如果t3用的是别的锁对象，那么t3不会存在与t2和t4线程争夺同一把锁的情况，
                         * 它们各不相干。
                         */
                        /**
                         * 如果t1线程执行Lock.notify()后，立即执行lock.wait()。由于wait会释放锁，所以t2会立即获取到锁（只存在t2一个线程的前提下）
                         * 这时打印list1的size才是5。
                         */
                        /**
                         * 那么有没有一种方式，可以在t1刚一发出通知时，t2立即就可以做出反应呢？
                         * 使用CountDownlatch，它与锁没有一毛钱关系，它不是锁。使用countDownLatch
                         * 时需要将synchronized和wait,notify相关的东西都注释掉。
                         */
                        throw new RuntimeException();
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t2.start();
        /**
         * 这里休眠5毫秒是为了保证t2线程是一定是最先启动的
         * 不加也可以，不是必须的。
         */
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
        /**
         * 注意，这里先让t2启动，因为t2只有先启动了，才能先进入wait状态，释放锁，否则，在t1线程结束前，t2是别想拿到锁的，因为t1在循环时
         * 也是加了synchronized锁的，所以t1如果不结束线程释放锁，t2是不会拿到锁的，所以要让t2先执行，如果条件不满足，就会进入wait状态，
         * wait状态是释放锁的，所以t1会拿到锁，然后执行。
         */
    }

}
