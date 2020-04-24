package threadcoreknowledge.threadobjectclasscommonmethods.printoddeven;

/**
 * 自己实现的
 * @author mfh
 * @date 2020/4/24 21:15
 */
public class Own {
    private static final Object lock = new Object();
    private static int num;

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            synchronized (lock) {
                while (num <= 100) {
                    lock.notify();
                    // 打印0以及所有偶数
                    System.out.println(Thread.currentThread().getName() + " 打印：" + num);
                    if (num == 100) {
                        lock.notifyAll();
                        break;
                    }
                    num++;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (num <= 100) {
                    lock.notify();
                    System.out.println(Thread.currentThread().getName() + " 打印：" + num);
                    // 打印所有奇数
                    if (num == 100) {
                        lock.notifyAll();
                        break;
                    }
                    num++;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t0.start();
        t1.start();
    }

}
