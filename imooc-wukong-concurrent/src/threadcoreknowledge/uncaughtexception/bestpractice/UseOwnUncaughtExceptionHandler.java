package threadcoreknowledge.uncaughtexception.bestpractice;

/**
 * 使用自定义异常处理器处理子线程异常
 * @author mfh
 * @date 2020/5/3 20:47
 */
public class UseOwnUncaughtExceptionHandler implements Runnable {
    public static void main(String... args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("自定义异常捕获器"));
        new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-1").start();
        Thread.sleep(100);
        new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-1").start();
        Thread.sleep(100);
        new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-1").start();
        Thread.sleep(100);
        new Thread(new UseOwnUncaughtExceptionHandler(), "Thread-1").start();
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
