package threadcoreknowledge.threadobjectclasscommonmethods.wait.analysisthreadstate;

/**
 * @author mfh
 * @date 2020/4/21 10:00
 */
public class ThreadB implements Runnable {
    private Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            lock.notify();
            System.out.println(Thread.currentThread().getName() + "调用了 notify()");
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
