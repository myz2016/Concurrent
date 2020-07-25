package threadcoreknowledge.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author mfh
 * @date 2020/7/19 10:14
 */
public class ThreadMXBeanDetection implements Runnable {
    static Object lockA = new Object();
    static Object lockB = new Object();
    private int flag;
    @Override
    public void run() {
        System.out.printf("%d%n", flag);
        String name = Thread.currentThread().getName();
        if (flag == 1) {
            synchronized (lockA) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println("线程：" + name + " 获取到了 lockB");
                }
            }
        }
        if (flag == 2) {
            synchronized (lockB) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.println("线程：" + name + " 获取到了 lockA");
                }
            }

        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadMXBeanDetection a = new ThreadMXBeanDetection();
        a.flag = 1;
        ThreadMXBeanDetection b = new ThreadMXBeanDetection();
        b.flag = 2;
        Thread t0 = new Thread(a, "t0");
        Thread t1 = new Thread(b, "t1");
        t0.start();
        t1.start();
        Thread.sleep(1000);
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        if (null != deadlockedThreads && deadlockedThreads.length > 0) {
            for (int i = 0; i < deadlockedThreads.length; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.printf("发现死锁：%s%n", threadInfo.getThreadName());
            }
        } else {
            System.out.println("没有发现死锁");
        }
    }
}
