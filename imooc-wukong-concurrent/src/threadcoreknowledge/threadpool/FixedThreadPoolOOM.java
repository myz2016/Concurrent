package threadcoreknowledge.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * jvm 启动参数设置：-Xmx1m -Xms1m
 * 描述：演示 newFixedThreadPool 出错的情况
 * @author mfh
 * @date 2020/7/25 22:14
 */
public class FixedThreadPoolOOM {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new Task());
        }
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                // 任务一直在睡觉
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
