package threadcoreknowledge.stopthreads;

/**
 * 每次迭代都阻塞线程的情况
 * @author mfh
 * @date 2020/4/13 22:11
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            int num = 0;
            try {
                while (num <= Integer.MAX_VALUE / 2) {
                    if (num % 100 == 0) {
                        System.out.printf("%d 是 100 的倍数\n", num);
                    }
                    Thread.sleep(10);
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
    /**
     * 当每次迭代都阻塞线程时，不需要 Thread.currentThread().isInterrupted()，也能中断线程
     */
}
