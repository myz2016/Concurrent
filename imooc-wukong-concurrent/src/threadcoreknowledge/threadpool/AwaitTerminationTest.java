package threadcoreknowledge.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author mfh
 * @date 2020/7/26 10:19
 */
public class AwaitTerminationTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new AwaitTerminationTask());
        }
        Thread.sleep(1500);
        // 调用了 shutdown()
        executorService.shutdown();
        // 上面调用的 shutdown() 方法并不用影响 awaitTermination 的结果，awaitTermination 检测的是 terminated，现在线程池还没有执行完所有任务，还在运行中，所以输出 false
        System.out.println("awaitTermination:" + executorService.awaitTermination(1, TimeUnit.SECONDS));
        // 等 7 秒后线程池所有任务都执行完毕
        TimeUnit.SECONDS.sleep(7);
        // 由于上面调用了 shutdown()，线程池已经进入了关闭状态，等了 7 秒之后，线程池所有任务都执行完毕了，所以线程池已经完全关闭了，所以 awaitTermination 检测输出 true
        // 如果上面没有调用了 shutdown()，这里依然会输出 false
        System.out.println("awaitTermination:" + executorService.awaitTermination(1, TimeUnit.SECONDS));
    }
}

class AwaitTerminationTask implements Runnable {

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
