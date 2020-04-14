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
     * 如果在执行过程中，每次循环都会调用 sleep 或 wait 等方法，那么不需要加入 Thread.currentThread().isInterrupted() ，每次都检查是否已中断，这是多余的判断。
     * 即使没有这样的判断，也能中断线程。
     */
}
