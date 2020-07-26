package threadcoreknowledge.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author mfh
 * @date 2020/7/25 19:51
 */
public class ForLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Task()).start();
        }
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }

}
