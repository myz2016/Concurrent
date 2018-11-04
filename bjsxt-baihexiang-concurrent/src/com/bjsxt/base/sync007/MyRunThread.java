package com.bjsxt.base.sync007;

public class MyRunThread {
    /** volatile */
    private volatile boolean isRunning = true;
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    public void run() {
        while (isRunning) {
            /**
             * 这个不能写 System.out..之类的操作，如果写了
             * 即使不适用 volatile 修饰 isRunning 变量，当
             * 第二个线程执行 setRunning(false) 方法时，第一
             * 个线程也会停止，也就是会有 volatile 的效果。
             * 这里应该和 System.out 有关系，可能是在调用
             * out的时候，又重新从主内存获取了变量值等等。
             * 这里先这么理解吧。所以打印的时候肯定会有影响。
             * 内部应该是 System.out 干扰了正在执行的这个线程，
             * 把变量从主内存刷了回来。在企业开发中都不建议使用
             * System.out，会浪费性能。
             */
        }
        System.out.println("线程停止！");
    }

    /**
     * MyRunThread 类并不是一个线程类，在 mian 方法中实例化了一个 mrt 对象。
     * 然后实例化了两个线程对象 t1,t2。两个线程对象启动，操作的都是 mrt 这一
     * 个对象，所以使用 volatile 修饰的变量还是可以保证线程安全性可见的。与
     * RunThread 不一样的是，RunThread 本身就是线程类，所以实例化两个线程对象，
     * 两个线程对象中会有两个 isRunning 变量。即使使用 volatile 变量修饰，也不会
     * 起到作用，也就是说 volatile 修饰的变量，是要在多个线程访问同一个对象的时候
     * 才会起作用，关键点还是同一个对象。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final MyRunThread mrt = new MyRunThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mrt.run();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
               mrt.setRunning(false);
            }
        }, "t2");

        t1.start();
        Thread.sleep(2000);
        t2.start();
    }
}
