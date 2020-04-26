package threadcoreknowledge.threadobjectclasscommonmethods.wait;

/**
 * 描述：3个线程，t0和t1首先被阻塞，t2唤醒 notifyAll() 唤醒它们。
 * start 先执行不代表先启动
 * @author mfh
 * @date 2020/4/24 12:17
 */
public class WaitNotifyAll implements Runnable {
    private static final Object resourceA = new Object();
    @Override
    public void run() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " 获取到资源锁！");
            try {
                System.out.println(Thread.currentThread().getName() + " 准备释放资源锁！");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName() + " 再次获取到资源锁，即将结束同步代码块！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        WaitNotifyAll wna = new WaitNotifyAll();
        Thread t0 = new Thread(wna);
        Thread t1 = new Thread(wna);
        Thread t2 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " 获取到资源锁！");
                System.out.println(Thread.currentThread().getName() + " 准备唤醒等待资源锁的线程");
                resourceA.notifyAll();
                System.out.println(Thread.currentThread().getName() + " 已发出唤醒信号，即将结束同步代码块！");
            }
        });
        t0.start();
        t1.start();
        Thread.sleep(200);
        t2.start();
    }
}
