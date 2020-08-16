package threadcoreknowledge.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mfh
 * @date 2020/8/8 19:36
 */
public class PessimismOptimismLock {
    int a;
    public static void main(String[] args) {
        // 乐观锁
        AtomicInteger a = new AtomicInteger();
        a.incrementAndGet();
    }

    // 悲观锁
    public synchronized void testMethod() {
        a++;
    }
}
