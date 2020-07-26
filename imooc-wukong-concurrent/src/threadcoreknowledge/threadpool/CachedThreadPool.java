package threadcoreknowledge.threadpool;

import javax.management.relation.RoleUnresolved;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mfh
 * @date 2020/7/25 22:56
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
    }
    static class Task implements Runnable {

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
}
