package threadcoreknowledge.threadobjectclasscommonmethods.join;

/**
 * 演示 join 期间被中断的效果
 * @author mfh
 * @date 2020/4/26 15:57
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        // 获取主线程
        Thread mainThread = Thread.currentThread();
        Thread t0 = new Thread(() -> {
            try {
                mainThread.interrupt();
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " 线程执行完毕！");
            } catch (InterruptedException e) {
                System.out.println("子线程执行完毕！");
                //TODO 收尾工作
            }
        });
        t0.start();
        System.out.println("线程 " + Thread.currentThread().getName() + " 等待子线程执行完毕！");
        try {
            t0.join();
        } catch (InterruptedException e) {
            System.out.println("线程 " + Thread.currentThread().getName() + " 被中断！");
            // 在主线程获取到中断时，将这个中断传递给子线程
            t0.interrupt();
        }
        System.out.println("所有子线程执行完毕！");
    }
}
