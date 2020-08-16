package threadcoreknowledge.lock.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mfh
 * @date 2020/8/16 13:49
 */
public class SpinLock {
    private static AtomicReference<Thread> sign = new AtomicReference<>();

     void lock() {
        Thread thread = Thread.currentThread();
        while (!sign.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + " 获取自旋锁失败，尝试重新获取.....");
        }
    }

     void unlock() {
        Thread thread = Thread.currentThread();
        sign.compareAndSet(thread, null);
    }
    
    public static void main(String[] args) {
        SpinLock sl = new SpinLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " 尝试获取自旋锁");
            sl.lock();
            System.out.println(Thread.currentThread().getName() + " 获取到了自旋锁");
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } finally {
                sl.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放了自旋锁");
            }
        };
        new Thread(runnable, "Thread0").start();
        new Thread(runnable, "Thread1").start();
    }
}
