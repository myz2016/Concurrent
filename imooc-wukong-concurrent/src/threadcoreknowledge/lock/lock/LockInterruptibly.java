package threadcoreknowledge.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mfh
 * @date 2020/8/8 13:46
 */
public class LockInterruptibly implements Runnable {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        LockInterruptibly li = new LockInterruptibly();
        Thread t0 = new Thread(li);
        Thread t1 = new Thread(li);
        t0.start();
        t1.start();
        Thread.sleep(1000);
        // 尝试在等锁期间被中断
        t1.interrupt();
    }
    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取到了锁");
                Thread.sleep(5000);
            } catch (InterruptedException exception) {
                System.out.println(Thread.currentThread().getName() + " 睡眠期间被中断");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 等锁期间被中断");
        }
    }
}