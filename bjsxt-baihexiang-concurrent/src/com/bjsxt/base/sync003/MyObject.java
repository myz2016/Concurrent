package com.bjsxt.base.sync003;

import sun.dc.pr.PRError;

/**
 * 同步：synchronized
 * 同步的概念就是共享，我们要牢牢记住“共享”这两个字，如果不是为了共享的资源，就没有必要进行同步。
 * 异步：asynchronized
 * 异步的概念就是独立，相互之间不受到任何制约。就像页面发起的ajax请求。
 * 同步的目的是为了线程安全，其实对于线程安全来说，需要满足两个条件：
 *  1、原子性（同步）
 *  2、可见性
 */
public class MyObject {
    private synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** synchronized */
    private void method2() {
        System.out.println(Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        final MyObject mo = new MyObject();
        /**
         * 分析：
         *  t1 线程先持有 object 对象的 lock 锁，t2 线程可以以异步的方式调用对象中的非 synchronized 修饰的方法
         *  t1 线程先持有 object 对象的 lock 锁，t2 线程如果在这个时候调用对象中的同步（synchronized）方法则需要等待，也就是同步
         *  如果两个线程同时访问一个同步的方法（前提肯定是同一对象锁），那么肯定只有一个线程可以先进入，另外一个线程则等待。
         *  如果两个线程一个访问同步的，一个访问异步的（不加 synchronized）方法，他们两个之间是没有任何关系的（即使是使用
         *  的同一个对象锁），是可以同时访问的，谁都不需要等待。所以如果想同步的话，method2 方法也要被 synchronized 关键字
         *  修饰。
         *  所以在写一个类的时候，如果这个类就是同步的，那么建议给此类中的所有关乎业务的方法，最好是都加上 synchronized 关键字，
         *  可以保证业务的完整性。 要加就都加锁，要不加都不加。
         *  或者是读写互斥锁：
         *      多少个线程同时读都可以，但如果读的同时有一个线程想进来写，那么是不允许的（这个定义说的不标准，可自行上网查阅）。
         */
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mo.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mo.method2();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
