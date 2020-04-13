package threadcoreknowledge.stopthreads;

/**
 * run 方法内没有 sleep 或 wait 方法时，停止线程
 * @author mfh
 * @date 2020/4/13 21:06
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        /**
         * Thread.currentThread().isInterrupted()
         * 实时监控着，观察着此线程是否被通知中断，如果通知了，就不再执行。
         * 这就是响应中断，如果此处不响应中断，线程对象调用 interrupt()
         * 是没有任何作用的
         */
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 100_00 == 0) {
                System.out.printf("%d 是 10000 的倍数\n", num);
            }
            num++;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        /**
         * 让子线程执行 1 秒，如果上来就中断，那么将不会打印任何内容
         */
        Thread.sleep(1000);
        /**
         * run 方法内如果没有响应中断，此处调用 interrupt() 是没有任何作用的
         */
        thread.interrupt();
    }
}
