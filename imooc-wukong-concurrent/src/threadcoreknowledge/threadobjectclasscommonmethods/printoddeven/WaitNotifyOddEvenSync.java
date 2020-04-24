package threadcoreknowledge.threadobjectclasscommonmethods.printoddeven;

/**
 * @author mfh
 * @date 2020/4/24 22:31
 */
public class WaitNotifyOddEvenSync {
    private static int num;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread t0 = new Thread(() -> {
            while (num < 100) {
                synchronized (lock) {
                    // 位运算之偶数
                    if ((num & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + num++);
                    }
                }
            }
        });

        Thread t1 = new Thread(() -> {
            while (num < 100) {
                synchronized (lock) {
                    // 位运算之奇数
                    if ((num & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + num++);
                    }
                }
            }
        });
        t0.start();
        t1.start();
    }
}
