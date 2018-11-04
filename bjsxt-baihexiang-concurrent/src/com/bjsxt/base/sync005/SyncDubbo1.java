package com.bjsxt.base.sync005;

/**
 * synchronized的重入
 * synchronize关键字拥有重入的功能，也就是在使用synchronize时，当一
 * 个线程得到一个对象的锁后，再次请求此对象时可以再次得到该对象的锁。
 * 下面的代码是没有问题的
 */
public class SyncDubbo1 {
    private synchronized void method1() {
        System.out.println("method1...");
        method2();
    }

    private synchronized void method2() {
        System.out.println("method2...");
        method3();
    }

    private synchronized void method3() {
        System.out.println("method3...");
    }
    public static void main(String[] args) {
        final SyncDubbo1 sd = new SyncDubbo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method1();
            }
        }, "t1");
        t1.start();
    }
}
