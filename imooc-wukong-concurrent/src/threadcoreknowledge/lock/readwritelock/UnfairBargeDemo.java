package threadcoreknowledge.lock.readwritelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：演示非公平和公平的 ReentrantReadWriteLock
 *
 * @author mfh
 * @date 2020/8/16 10:18
 */
public class UnfairBargeDemo {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(() -> write(), "Thread1").start();
        new Thread(() -> read(), "Thread2").start();
        new Thread(() -> read(), "Thread3").start();
        new Thread(() -> write(), "Thread4").start();
        new Thread(() -> read(), "Thread5").start();
        new Thread(() -> {
            int num = 10000;
            Thread[] threads = new Thread[num];
            for (int i = 0; i < num; i++) {
                threads[i] = new Thread(() -> read(), "子线程创建的 Thread" + i);
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
        }).start();

    }
    static void read() {
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 得到读锁，尝试读取");
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            readLock.unlock();
        }
    }

    static void write() {
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 得到写锁，尝试写入");
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
            writeLock.unlock();
        }
    }
}
