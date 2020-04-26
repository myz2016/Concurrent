package threadcoreknowledge.threadobjectclasscommonmethods.join;

/**
 * 演示 join 期间，线程状态
 * @author mfh
 * @date 2020/4/26 17:03
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();
        Thread t0 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("主线程状态：" + main.getState());
                System.out.println("子线程执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t0.start();
        System.out.println("等待子线程运行完毕");
        t0.join();
        System.out.println("所以线程执行完毕");
    }
}
