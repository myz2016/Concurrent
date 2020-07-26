package threadcoreknowledge.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：演示每个任务执行前后放钩子函数
 * @author mfh
 * @date 2020/7/26 13:10
 */
public class PauseableThreadPool extends ThreadPoolExecutor {

    private boolean pauseFlag;
    // 为了 pauseFlag 能够并发安全，需要在操作的时加锁
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public PauseableThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public static void main(String[] args) throws InterruptedException {
        PauseableThreadPool ptp = new PauseableThreadPool(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 10000; i++) {
            ptp.execute(runnable);
        }
        Thread.sleep(2500);
        ptp.pause();
        System.out.println("暂停线程池");
        Thread.sleep(1500);
        ptp.resume();
        System.out.println("恢复线程池");
    }
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
        try {
            while (pauseFlag) {
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 暂停
     */
    private void pause() {
        lock.lock();
        try {
            pauseFlag = true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 恢复
     */
    private void resume() {
        lock.lock();
        try {
            pauseFlag = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
