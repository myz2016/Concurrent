package threadcoreknowledge.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：用 tryLock 来避免死锁
 * @author mfh
 * @date 2020/8/8 12:12
 */
public class TryLockDeadlock implements Runnable {
    private Lock lock0 = new ReentrantLock();
    private Lock lock1 = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        TryLockDeadlock tdl = new TryLockDeadlock();
        Thread t0 = new Thread(tdl, "t0");
        Thread t1 = new Thread(tdl, "t1");
        t0.start();
        t1.start();
        t0.join();
        t1.join();

    }
    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("t0")) {
            while (true) {
                sleep();
                if (lock0.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 获取到了 lock0");
                        if (lock1.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " 获取到了 lock1，两把锁都获取到了，开始执行任务....");
                                Thread.sleep(1000);
                                break;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                lock1.unlock();
                                sleep();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + " 没有获取到 lock1，准备进行重试....");
                        }
                    } finally {
                        lock0.unlock();
                        sleep();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " 没有获取到 lock0，准备进行重试....");
                }
            }
        } else {
            while (true) {
                sleep();
                if (lock1.tryLock()) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 获取到了 lock1");
                        if (lock0.tryLock()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " 获取到了 lock0，两把锁都获取到了，开始执行任务....");
                                Thread.sleep(1000);
                                break;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                lock0.unlock();
                                sleep();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + " 没有获取到 lock0，准备进行重试....");
                        }
                    } finally {
                        lock1.unlock();
                        sleep();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " 没有获取到 lock1，准备进行重试....");
                }
            }
        }
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
