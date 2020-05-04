package threadcoreknowledge.sync.classlock;

import java.util.concurrent.TimeUnit;

/**
 * 类锁的一种形式：static 同步方法
 * @author mfh
 * @date 2020/5/3 23:17
 */
public class SyncClassStatic implements Runnable {
    static SyncClassStatic syncClassStatic0 = new SyncClassStatic();
    static SyncClassStatic syncClassStatic1 = new SyncClassStatic();
    @Override
    public void run() {
        try {
            method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static synchronized void method() throws InterruptedException {
        System.out.println("我是类锁的第一种形式：static 形式，我叫：" + Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + "运行结束");
    }

    public static void main(String[] args) throws InterruptedException {
        // syncClassStatic0、syncClassStatic1 的声明位置无所谓，静态成员变量与局部变量效果是一样的
        /*SyncClassStatic syncClassStatic0 = new SyncClassStatic();
        SyncClassStatic syncClassStatic1 = new SyncClassStatic();*/
        Thread t0 = new Thread(syncClassStatic0);
        Thread t1 = new Thread(syncClassStatic1);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }
}
