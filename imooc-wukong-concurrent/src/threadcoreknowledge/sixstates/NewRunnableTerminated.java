package threadcoreknowledge.sixstates;

/**
 * 演示了线程的三种状态，分别是：
 * 1、New
 * 2、Runnable
 * 3、Terminated
 * @author mfh
 * @date 2020/4/19 20:27
 */
public class NewRunnableTerminated implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerminated());
        // 状态：NEW
        System.out.println(thread.getState());
        thread.start();
        // 状态：RUNNABLE
        System.out.println(thread.getState());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 此时线程 t 一定是在运行中的，因为主线程休眠了 10 毫秒，t 线程有机会运行
        // 线程状态：RUNNABLE
        System.out.println(thread.getState());
        // 此处休眠为了让 t 线程一定执行完毕
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程状态：TERMINATED
        System.out.println(thread.getState());
    }
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
