package threadcoreknowledge.threadobjectclasscommonmethods.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep 不会释放 lock 锁（lock 锁需要手动释放）
 * @author mfh
 * @date 2020/4/26 13:47
 */
public class SleepDontReleaseLock implements Runnable {
    private static final Lock LOCK = new ReentrantLock();

    @Override
    public void run() {
        LOCK.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 获取到了 lock 锁！");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " 已经苏醒！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
    
    public static void main(String[] args) {
        SleepDontReleaseLock demo = new SleepDontReleaseLock();
        Thread t0 = new Thread(demo);
        Thread t1 = new Thread(demo);
        t0.start();
        t1.start();
    }
}
