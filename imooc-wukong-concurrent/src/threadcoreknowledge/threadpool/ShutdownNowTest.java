package threadcoreknowledge.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mfh
 * @date 2020/7/26 11:01
 */
public class ShutdownNowTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutdownNowTask(i));
        }
        Thread.sleep(1500);
        List<Runnable> taskInQueue = executorService.shutdownNow();
        System.out.println("队列中任务个数：" + taskInQueue.size());
        StringBuffer ids = new StringBuffer();
        for (Runnable runnable : taskInQueue) {
            ShutdownNowTask st = (ShutdownNowTask)runnable;
            ids.append(st.getId()).append(",");
        }
        System.out.println("队列中的任务：" + ids);
    }
}

class ShutdownNowTask implements Runnable {
    private int id;

    public ShutdownNowTask(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("任务：" + this.getId() + " ----- " + Thread.currentThread().getName() + " 被中断了");
        }
        System.out.println("任务：" + this.getId() + " ----- " + Thread.currentThread().getName());
    }
}

