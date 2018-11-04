package com.bjsxt.base.sync006;

public class TestLock {
    private Object lock = new Object();

    /**
     * 说明：
     * 1、t1线程启动后调用method
     * 2、t2线程启动后调用method
     * 3、t1,t2线程公用一个锁lock
     * method方法本身并没有加锁，所以t1和t2线程会同时进入到method方法。
     * 4、如果 System.out.println(Thread.currentThread().getName() + "进入"); 代码写在method方法的第一句的话，
     * 会看见几乎同时打印 t1进入，t2进入;
     * 5、如果将其写在锁定代码块的第一句,那么总是会同时看见 xxx进入,xxx进入lock块.
     * 并且xxx的值肯定是一样的。比如t1先竞争到了锁，那么xxx就是t1,当休眠结束后，锁
     * 定代码块里面的代码就执行完了，所以t1就会释放锁，这时t2会竞争到锁。所以会看见
     * t2进入，t2进入lock块同时打印了出来，然后才是t1离开lock块。但有时也不一定，也
     * 有可能是先打印t1离开Lock块，然后在打印t2进入，t2进入lock块。
     */
    private void method() {
        System.out.println(Thread.currentThread().getName() + "进入");
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + "进入lock块");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "离开lock块");
    }

    public static void main(String[] args) {
        final TestLock lock = new TestLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.method();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
