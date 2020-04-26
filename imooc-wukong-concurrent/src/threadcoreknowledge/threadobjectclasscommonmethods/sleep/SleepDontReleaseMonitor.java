package threadcoreknowledge.threadobjectclasscommonmethods.sleep;

/**
 * sleep 不会释放 monitor 锁
 * @author mfh
 * @date 2020/4/26 13:43
 */
public class SleepDontReleaseMonitor implements Runnable {

    @Override
    public void run() {
        this.sync();
    }

    private synchronized void sync() {
        System.out.println(Thread.currentThread().getName() + " 获取到了 monitor 锁！");
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " 苏醒了！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseMonitor demo = new SleepDontReleaseMonitor();
        Thread t0 = new Thread(demo);
        Thread t1 = new Thread(demo);
        t0.start();
        t1.start();
    }
}
