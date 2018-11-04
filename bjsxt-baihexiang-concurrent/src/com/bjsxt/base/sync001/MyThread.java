package com.bjsxt.base.sync001;

public class MyThread extends Thread {
    private int count = 5;

    /**
     * 当run方法没有加synchronize关键字时，则不是线程安全的。
     * synchronize加锁
     */
    @Override
    public synchronized void run() {
        count--;
        System.out.println(this.currentThread().getName() + " count=" + count);
    }
    public static void main(String[] args) {
        /**
         * 分析：当多个线程访问myThread的run方法时，每一个线程是以排队的方式处理（这里排队是按照CPU分配的先后顺序而定的
         *       而不是按照代码写的顺序定的，比如代码写的是t1先start，然后是t2，但是真正的执行起来时，不一定是t1先start），
         *      一个线程想要执行synchroinzed修饰的方法里的代码：
         *         1、尝试获得锁
         *         2、如果拿到锁，执行synchronized代码体内容；拿不到锁，这个线程会不断的尝试获得这把锁，直到拿到为止，
         *            而且是多个线程同时去竞争这把锁（也就是会有锁竞争问题）
         *       上面说到此程序会涉及到多个线程竞争同一把锁的问题，那么竞争的这把锁是谁呢？这把锁就是myThread对象的对象
         *       锁。因为多个线程访问的都是这一个myThread对象，run方法上又加入了synchronized关键字，所以多个线程如果实
         *       现同步，竞争的肯定是myThread这个对象的对象锁。
         *       代码如果变成这样：
         *          MyThread myThread2 = new MyThread();
         *          Thread t6 = new Thread(myThread2, t6);
         *          Thread t7 = new Thread(myThread2, t7);
         *          t6.start();
         *          t7.start();
         *       线程t6,t7与线程t1至t5就不存在锁竞争问题，因为t6和t7访问的是myThread2对象，他俩竞争的是myThread2对象的锁，
         *       与t1至t5线程的竞争的myThread对象锁没有任何关系。
         */
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread, "t1");
        Thread t2 = new Thread(myThread, "t2");
        Thread t3 = new Thread(myThread, "t3");
        Thread t4 = new Thread(myThread, "t4");
        Thread t5 = new Thread(myThread, "t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        /**
         * 注意：这里只有一个myThread对象，将这一个对象放进了多个线程中，也就是多个线程访问这一个对象。
         * 线程安全也就是当有多个线程访问同一个类时，这个类还能表现出正确的结果，那么这个类就是线程安全的。
         */
    }
}
