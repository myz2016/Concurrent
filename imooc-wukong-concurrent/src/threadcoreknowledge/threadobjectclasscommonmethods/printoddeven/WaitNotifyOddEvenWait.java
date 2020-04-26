package threadcoreknowledge.threadobjectclasscommonmethods.printoddeven;

import org.junit.Before;
import org.junit.Test;

/**
 * @author mfh
 * @date 2020/4/24 22:06
 */
public class WaitNotifyOddEvenWait {
    private static int count;
    private static final Object lock = new Object();
    private TurningRunner tr;
    static class TurningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "：" + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Before
    public void before() {
        tr = new TurningRunner();
    }
    @Test
    public void withThreadName() throws InterruptedException {
        Thread t0 = new Thread(tr, "偶数");
        Thread t1 = new Thread(tr, "奇数");
        t0.start();
        // 休眠保证 t0 先抢到锁，先打印 0，因为它是打印偶数的线程
        Thread.sleep(10);
        t1.start();
        t0.join();
        t1.join();
    }

    @Test
    public void withoutThreadName() throws InterruptedException {
        // 不区分线程名字，先打印的线程为偶数线程，所以此处不需要休眠
        Thread t0 = new Thread(tr);
        Thread t1 = new Thread(tr);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }
}
