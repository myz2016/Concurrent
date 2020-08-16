package threadcoreknowledge.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：用 tryLock 来避免死锁
 * @author mfh
 * @date 2020/8/8 12:12
 */
public class TryLockTimeDeadlock implements Runnable {
    private Lock lock0 = new ReentrantLock();
    private Lock lock1 = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        TryLockTimeDeadlock tdl = new TryLockTimeDeadlock();
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
                try {
                    if (lock0.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " 获取到了 lock0");
                            if (lock1.tryLock()) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + " 获取到了 lock1，两把锁都获取到了，开始执行任务....");
                                    sleep(new Random().nextInt(1000));
                                    break;
                                } finally {
                                    // 解锁后让线程等一会再去重试，给其他线程留点获取该锁的时间
                                    lock1.unlock();
                                    sleep(new Random().nextInt(100));
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + " 没有获取到 lock1，准备进行重试....");
                            }
                        } finally {
                            // 解锁后让线程等一会再去重试，给其他线程留点获取该锁的时间
                            lock0.unlock();
                            sleep(new Random().nextInt(100));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 没有获取到 lock0，准备进行重试....");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            while (true) {
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " 获取到了 lock1");
                            if (lock0.tryLock()) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + " 获取到了 lock0，两把锁都获取到了，开始执行任务....");
                                    sleep(new Random().nextInt(1000));
                                    break;
                                } finally {
                                    // 解锁后让线程等一会再去重试，给其他线程留点获取该锁的时间
                                    lock0.unlock();
                                    sleep(new Random().nextInt(100));
                                }
                            } else {
                                System.out.println(Thread.currentThread().getName() + " 没有获取到 lock0，准备进行重试....");
                            }
                        } finally {
                            lock1.unlock();
                            // 解锁后让线程等一会再去重试，给其他线程留点获取该锁的时间
                            sleep(new Random().nextInt(100));
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 没有获取到 lock1，准备进行重试....");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
