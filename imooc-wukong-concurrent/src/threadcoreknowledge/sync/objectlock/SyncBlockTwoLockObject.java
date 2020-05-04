package threadcoreknowledge.sync.objectlock;

/**
 * 使用两个不同对象锁的同步代码块
 * @author mfh
 * @date 2020/5/3 22:19
 */
public class SyncBlockTwoLockObject implements Runnable {
    private Object lock0 = new Object();
    private Object lock1 = new Object();

    @Override
    public void run() {
        synchronized (lock0) {
            System.out.println("我是 lock0，我叫：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock0 部分运行结束");
        }

        synchronized (lock0) {
            System.out.println("我是 lock1，我叫：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " lock1 部分运行结束");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        SyncBlockTwoLockObject tlo = new SyncBlockTwoLockObject();
        Thread t0 = new Thread(tlo);
        Thread.sleep(100);
        Thread t1 = new Thread(tlo);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }
}
