package threadcoreknowledge.stopthreads;

/**
 * run 方法内有 sleep 或 wait 方法时，停止线程
 *
 * @author mfh
 * @date 2020/4/13 21:29
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            int num = 0;
            try {
                while (/*!Thread.currentThread().isInterrupted() &&*/ num <= Integer.MAX_VALUE / 2) {
                    if (num % 100 == 0) {
                        System.out.printf("%d 是 100 的倍数\n", num);
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
    /**
     * 当线程正在休眠的过程中，如果收到了中断信号，于是便会响应这个中断，它响应中断的方式比较特殊，是通过抛出异常的方式来响应中断。
     * 当程序带有 sleep 或者 wait 这种能让线程进行阻塞的方法时，我们需要中断的时候所需要注意的。它会让调用者处理 interruptException
     * 处理掉，我们选择 catch 的方式处理掉之后，就可以做到，当线程进入阻塞过程中，依然能够响应中断。
     */
}
