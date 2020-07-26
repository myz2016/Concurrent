package threadcoreknowledge.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author mfh
 * @date 2020/7/25 21:58
 */
public class FixedThreadPoolTest {
   public static void main(String[] args) {
       ExecutorService executorService = Executors.newFixedThreadPool(4);
       for (int i = 0; i < 1000; i++) {
           executorService.execute(new Task());
       }
   }
    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.MICROSECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
