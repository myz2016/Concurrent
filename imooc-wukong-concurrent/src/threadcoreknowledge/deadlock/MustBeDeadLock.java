package threadcoreknowledge.deadlock;

/**
 * @author mfh
 * @date 2020/7/19 10:28
 */
public class MustBeDeadLock implements Runnable {
    private Object lockA = new Object();
    private Object lockB = new Object();
    public static void main(String[] args) {
        MustBeDeadLock mustBeDeadLock = new MustBeDeadLock();
        Thread t0 = new Thread(mustBeDeadLock, "t0");
        Thread t1 = new Thread(mustBeDeadLock, "t1");
        t0.start();
        t1.start();
    }

    @Override
    public void run() {
        System.out.printf("当前线程：%s%n", Thread.currentThread().getName());
        if (Thread.currentThread().getName().equals("t0")) {
            synchronized (lockA) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.printf("%s获取到了lockB", Thread.currentThread().getName());
                }
            }
        } else {
            synchronized (lockB) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA) {
                    System.out.printf("%s获取到了lockA", Thread.currentThread().getName());
                }
            }
        }
    }
}
