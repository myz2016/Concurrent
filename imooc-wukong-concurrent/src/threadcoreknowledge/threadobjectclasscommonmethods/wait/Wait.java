package threadcoreknowledge.threadobjectclasscommonmethods.wait;

/**
 * 演示 wait 和 notify 的基本用法
 * 1、研究代码执行顺序
 * 2、证明 wait 释放锁
 * @author mfh
 * @date 2020/4/19 22:15
 */
public class Wait {
    private final static Object lock = new Object();

    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                lock.notify();
                System.out.println(Thread.currentThread().getName() + "调用了 notify()");
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        t1.start();
        Thread.sleep(200);
        t2.start();
    }
}
