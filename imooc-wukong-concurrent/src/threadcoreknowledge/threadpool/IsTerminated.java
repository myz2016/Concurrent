package threadcoreknowledge.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author mfh
 * @date 2020/7/26 0:16
 */
public class IsTerminated {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new IsTerminatedTask());
        }
        // 先执行 1.5s 再说
        Thread.sleep(1500);
        System.out.println("执行了 shutdown() 方法");
        // 调用线程池停止方法（调用之后，线程池不会立刻停止，它会优雅的执行完存量任务）
        executorService.shutdown();
        // 等待 10 秒钟，再看线程池 terminated 状态
        TimeUnit.SECONDS.sleep(10);
        System.out.println("isTerminated:" + executorService.isTerminated());
    }
}

class IsTerminatedTask implements Runnable {

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
