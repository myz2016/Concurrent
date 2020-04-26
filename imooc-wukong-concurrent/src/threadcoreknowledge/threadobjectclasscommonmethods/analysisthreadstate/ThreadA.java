package threadcoreknowledge.threadobjectclasscommonmethods.analysisthreadstate;

/**
 * @author mfh
 * @date 2020/4/21 9:59
 */
public class ThreadA implements Runnable {
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + "开始执行了");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获取到了锁");
        }
    }
}
