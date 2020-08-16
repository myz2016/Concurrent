package threadcoreknowledge.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：Lock 不会像 synchronized 一样在异常时自动释放锁，因此最佳实践是，在 finally 中释放锁，以保证发生异常时锁一定被释放
 * @author mfh
 * @date 2020/8/8 12:00
 */
public class MustUnlock {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        } finally {
            lock.unlock();
        }
    }
}
