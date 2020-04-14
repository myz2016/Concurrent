package threadcoreknowledge.stopthreads.bestpractice;

/**
 * 最佳实践：catch 了 InterruptException 之后的优先选择：在方法签名中抛出异常
 * @author mfh
 * @date 2020/4/14 22:11
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.printf("业务处理...\n");
            throwInMethod();
        }
    }

    private void throwInMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new RightWayStopThreadInProd());
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
}
