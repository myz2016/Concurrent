package threadcoreknowledge.sync.classlock;

import java.util.concurrent.TimeUnit;

/**
 * 类锁的一种形式：synchronized(*.class)
 * @author mfh
 * @date 2020/5/4 9:29
 */
public class SyncClassClass implements Runnable {
    static SyncClassStatic syncClassStatic0 = new SyncClassStatic();
    static SyncClassStatic syncClassStatic1 = new SyncClassStatic();
    @Override
    public void run() {
        try {
            synchronized (SyncClassClass.class) {
                System.out.println("我是类锁的第一种形式：static 形式，我叫：" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "运行结束");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(syncClassStatic0);
        Thread t1 = new Thread(syncClassStatic1);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }
}
