package threadcoreknowledge.createthreads;

/**
 * 用 Thread 方式实现线程
 * @author mfh
 * @date 2020/4/12 21:34
 */
public class ThreadStyle extends Thread {
    @Override
    public void run() {
        System.out.println("用 Thread 类实现线程");
    }
    public static void main(String[] args) {
        Thread thread = new ThreadStyle();
        thread.start();
    }
}
