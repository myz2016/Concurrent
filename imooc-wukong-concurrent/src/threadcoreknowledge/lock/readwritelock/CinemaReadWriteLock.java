package threadcoreknowledge.lock.readwritelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：演示读写锁
 * @author mfh
 * @date 2020/8/15 9:28
 */
public class CinemaReadWriteLock {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到读锁");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            readLock.unlock();
        }
    }

    public void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到写锁");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
            writeLock.unlock();
        }
    }
    
    public static void main(String[] args) {
        CinemaReadWriteLock cinemaReadWriteLock = new CinemaReadWriteLock();
        new Thread(() -> cinemaReadWriteLock.write()).start();;
        new Thread(() -> cinemaReadWriteLock.read()).start();
        new Thread(() -> cinemaReadWriteLock.read()).start();
        new Thread(() -> cinemaReadWriteLock.write()).start();;
        new Thread(() -> cinemaReadWriteLock.read()).start();
    }
}
