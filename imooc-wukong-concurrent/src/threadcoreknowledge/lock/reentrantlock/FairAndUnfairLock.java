package threadcoreknowledge.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mfh
 * @date 2020/8/15 9:18
 */
public class FairAndUnfairLock {
    public static void main(String[] args) {
        PrintQueue pq = new PrintQueue();
        new Thread(() -> pq.print(null)).start();
        new Thread(() -> pq.print(null)).start();;
        new Thread(() -> pq.print(null)).start();;
        new Thread(() -> pq.print(null)).start();;
    }
}

class PrintQueue {
    // 公平锁
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    /**
     * 每个线程会打印两次
     * @param object
     */
    void print(String object) {
        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到锁，准备开始第一次打印");
            int second = new Random().nextInt(3);
            sleep(second);
            System.out.println(Thread.currentThread().getName() + " 第一次打印耗时 " + second + " 秒");
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放锁");
            reentrantLock.unlock();
        }

        reentrantLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到锁，准备开始第二次打印");
            int second = new Random().nextInt(3);
            sleep(second);
            System.out.println(Thread.currentThread().getName() + " 第二次打印耗时 " + second + " 秒");
            System.out.println(Thread.currentThread().getName() + " 打印完毕");
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放锁");
            reentrantLock.unlock();
        }
    }

    static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
