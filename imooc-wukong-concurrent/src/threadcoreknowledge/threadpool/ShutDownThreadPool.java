package threadcoreknowledge.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mfh
 * @date 2020/7/26 0:16
 */
public class ShutDownThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new IsTerminatedTask());
        }
        // 先执行 1.5s 再说
        Thread.sleep(1500);
        // 调用线程池停止方法（调用之后，线程池不会立刻停止，它会优雅的执行完存量任务）
        executorService.shutdown();
        System.out.println("执行了 shutdown() 方法");
        Thread.sleep(1000);
        // 执行 shutdown() 之后再次提交任务，会抛出拒绝异常
        executorService.execute(new IsTerminatedTask());
    }
}
class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
