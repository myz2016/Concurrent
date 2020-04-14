package threadcoreknowledge.stopthreads.bestpractice;

/**
 * 最佳实践：catch 了 InterruptException 之后的优先选择：在方法签名中抛出异常
 *
 * @author mfh
 * @date 2020/4/14 22:11
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.printf("业务处理...\n");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.printf("记录日志\n");
                System.out.printf("停止程序\n");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadInProd());
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
}
