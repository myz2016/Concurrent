package threadcoreknowledge.threadobjectclasscommonmethods.join;

import java.util.concurrent.TimeUnit;

/**
 * 演示 join，注意语句输出顺序，会变化。
 * @author mfh
 * @date 2020/4/26 15:38
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t0.start();
        t1.start();
        System.out.println(Thread.currentThread().getName() + " 等待所有线程执行完毕！");
        t0.join();
        t1.join();
        System.out.println("所有子线程执行完毕");
    }
}
