package threadcoreknowledge.deadlock;

/**
 * @author mfh
 * @date 2020/7/19 10:14
 */
public class MustDeadLock implements Runnable {
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
    public static void main(String[] args) {
        MustDeadLock a = new MustDeadLock();
        a.flag = 1;
        MustDeadLock b = new MustDeadLock();
        b.flag = 2;
        Thread t0 = new Thread(a, "t0");
        Thread t1 = new Thread(b, "t1");
        t0.start();
        t1.start();
    }
}
