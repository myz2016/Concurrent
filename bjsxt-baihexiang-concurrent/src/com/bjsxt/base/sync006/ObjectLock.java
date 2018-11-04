package com.bjsxt.base.sync006;

public class ObjectLock {
    public void method1() {
        //对象锁
        synchronized(this) {
            try {
                System.out.println("do method1...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() {
        //类锁
        synchronized (ObjectLock.class) {
            try {
                System.out.println("do method2...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private Object lock = new Object();
    public void method3() {
        //任何对象锁
        synchronized (lock) {
            try {
                System.out.println("do method3...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock objectLock = new ObjectLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method1();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method2();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                objectLock.method3();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
