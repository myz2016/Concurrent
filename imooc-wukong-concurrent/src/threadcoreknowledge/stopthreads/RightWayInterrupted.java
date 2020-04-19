package threadcoreknowledge.stopthreads;

/**
 * 注意：Thread.interrupted() 方法的目标对象是 “当前线程”，而不管本方法来自于哪个对象
 * @author mfh
 * @date 2020/4/19 11:13
 */
public class RightWayInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (; ; ) {

            }
        });
        // 启动线程
        t.start();
        // 设置中断标志
        t.interrupt();
        // 获取中断标志
        System.out.println("isInterrupted:" + t.isInterrupted());
        // 获取中断标志并重置
        System.out.println("isInterrupted:" + t.interrupted());
        // 获取中断标志并重置
        System.out.println("isInterrupted:" + Thread.interrupted());
        // 获取中断标志
        System.out.println("isInterrupted:" + t.isInterrupted());
        t.join();
        System.out.println("Main thread is over");
    }
}
