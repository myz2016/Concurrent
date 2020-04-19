package threadcoreknowledge.sixstates;

/**
 * 演示 BLOCKED，WAITING，TIMED_WAITING 状态
 * @author mfh
 * @date 2020/4/19 20:46
 */
public class BlockedWaitingTimedWaiting implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting bwtw = new BlockedWaitingTimedWaiting();
        Thread t1 = new Thread(bwtw);
        Thread t2 = new Thread(bwtw);
        t1.start();
        t2.start();
        // 线程状态：TIMED_WAITING，因为正在执行 Thread.sleep(1000);
        System.out.println("t1 状态：" + t1.getState());
        // 线程状态：BLOCKED，因为 t2 想拿到 sync() 锁却拿不到
        System.out.println("t2 状态：" + t2.getState());
        // 保证已调用 wait() 方法
        Thread.sleep(1300);
        // 线程状态：WAITING，因为执行了 wait() 方法
        System.out.println("t1 状态：" + t1.getState());
    }
    @Override
    public void run() {
        this.sync();
    }

    private synchronized void sync() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
