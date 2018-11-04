package com.bjsxt.base.sync006;

public class ChangeLock {
    private String lock = "lock";
    private void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程" + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程" + Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        final ChangeLock changeLock = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t2");
        t1.start();
        t2.start();
        /**
         * 这个例子想讲明的一个问题就是，这个程序中的lock锁，对于t2线程来说，加了和没加是一样的。
         * t1线程先获取到了锁，lock锁，然后改变了锁的值，变成了change lock。即使锁的值已经改变
         * 了，但是现在t1持有的锁仍然是lock锁。t2线程启动，持有的锁是change lock锁，所以不会等
         * 待t1的锁，所以会t2会立即执行。
         */
    }
}
