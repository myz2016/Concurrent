package com.bjsxt.base.sync006;

public class DeadLock {
    private final static Object lockA = new Object();
    private static String lockB = "lockB";

    public void methodA() {
        synchronized (lockB) {
        }
    }

    public void methodB() {
        synchronized (lockA) {
        }
    }

    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA) {
                    deadLock.methodA();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB) {
                    deadLock.methodB();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
