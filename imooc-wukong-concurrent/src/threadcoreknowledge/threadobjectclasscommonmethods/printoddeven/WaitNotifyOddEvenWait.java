package threadcoreknowledge.threadobjectclasscommonmethods.printoddeven;

/**
 * @author mfh
 * @date 2020/4/24 22:06
 */
public class WaitNotifyOddEvenWait {
    private static int count;
    private static final Object lock = new Object();
    static class TurnningRunner implements Runnable {
        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "：" + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TurnningRunner tr = new TurnningRunner();
        /*Thread t0 = new Thread(tr, "偶数");
        Thread t1 = new Thread(tr, "奇数");*/
        Thread t0 = new Thread(tr);
        Thread t1 = new Thread(tr);
        t0.start();
//        Thread.sleep(10);
        t1.start();
    }
}
