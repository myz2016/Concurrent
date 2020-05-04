package threadcoreknowledge.uncaughtexception;

/**
 * 1.不加 try catch 抛出4个异常
 * 2.加了 try catch，期望捕获到第一个线程的异常，线程234不应该运行，希望看到打印 Caught Exception
 * 3.执行时发现，根本没有 Caught Exception，线程234依然运行并且抛出异常
 * 说明线程的异常不能用传统方法捕获
 * @author mfh
 * @date 2020/5/3 20:06
 */
public class CantCatchDirectly implements Runnable {
    public static void main(String... args) {
        try {
            new Thread(new CantCatchDirectly(), "Thread-1").start();
            new Thread(new CantCatchDirectly(), "Thread-1").start();
            new Thread(new CantCatchDirectly(), "Thread-1").start();
            new Thread(new CantCatchDirectly(), "Thread-1").start();
        } catch (Exception e) {
            System.out.println("Caught Exception.");
        }
    }
    @Override
    public void run() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("Caught Exception.");
        }
    }
}
