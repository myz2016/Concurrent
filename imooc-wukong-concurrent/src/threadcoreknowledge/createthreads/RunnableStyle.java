package threadcoreknowledge.createthreads;

/**
 * 使用 Runnable 方式创建线程
 *
 * @author mfh
 * @date 2020/4/12 21:32
 */
public class RunnableStyle implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();

    }
    @Override
    public void run() {
        System.out.println("用 Runnable 方法实现线程");
    }
}
