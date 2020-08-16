package threadcoreknowledge.lock.readwritelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述：演示读写锁
 * @author mfh
 * @date 2020/8/15 9:28
 */
public class UpgradeLock {
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public void readUpgrading() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到读锁");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("升级会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " 获取到了写锁，升级成功");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            readLock.unlock();
        }
    }

    public void writeDowngrading() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到写锁");
            TimeUnit.SECONDS.sleep(1);
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " 在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
            writeLock.unlock();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        UpgradeLock cinemaReadWriteLock = new UpgradeLock();
        System.out.println("先演示降级是可以的");
        Thread t0 = new Thread(() -> cinemaReadWriteLock.writeDowngrading());
        t0.start();
        // 等待 t0 执行完成
        t0.join();
        System.out.println("--------------------------------------------------");
        Thread t1 = new Thread(() -> cinemaReadWriteLock.readUpgrading());
        t1.start();
    }
}
