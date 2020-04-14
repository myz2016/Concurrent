package threadcoreknowledge.stopthreads.bestpractice;

/**
 * 最佳实践2：在 catch 子句中调用 Thread.currentThread().interrupt() 来恢复
 * 设置中断状态，以便于在后续的执行中，依然能够检查到刚才发生的中断
 *
 * @author mfh
 * @date 2020/4/14 22:11
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.printf("业务处理...\n");
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("停止运行");
                break;
            }
            throwInMethod();
        }
    }

    private void throwInMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadInProd2());
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
}
