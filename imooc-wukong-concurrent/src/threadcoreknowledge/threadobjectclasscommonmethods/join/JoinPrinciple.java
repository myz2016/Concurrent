package threadcoreknowledge.threadobjectclasscommonmethods.join;

import java.util.concurrent.TimeUnit;

/**
 * 通过讲解 join 原理，分析出 join 的替代写法
 * @author mfh
 * @date 2020/4/26 18:09
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t0.start();
        System.out.println(Thread.currentThread().getName() + " 等待所有线程执行完毕！");
        synchronized (t0) {
            t0.wait();
        }
        System.out.println("所有子线程执行完毕");
    }
}
