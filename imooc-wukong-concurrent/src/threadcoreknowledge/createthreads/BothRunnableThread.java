package threadcoreknowledge.createthreads;

/**
 * 同时继承 Thread 类，又实现 Runnable 接口
 * @author mfh
 * @date 2020/4/12 21:56
 */
public class BothRunnableThread extends Thread implements Runnable {
    /**
     * 使用此种方式，最终会输出 我来自于 Thread。
     * 原因： 我来自于 Runnable 的 run 方法是实现接口后重写的方法，
     * 而 我来自于 Thread 的 run 方法是继承后重写的方法，继承后重写
     * 的方法覆盖了接口实现的重写的方法。
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自于 Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自于 Thread");
            }
        }.start();
    }
}
