package threadcoreknowledge.lock.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mfh
 * @date 2020/8/9 11:50
 */
public class CinemaBookSeat {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        CinemaBookSeat cbs = new CinemaBookSeat();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
        new Thread(() -> bookSeat()).start();
    }
    private static void bookSeat() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始预订座位");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " 完成预订座位");
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
