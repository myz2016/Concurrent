package threadcoreknowledge.threadpool;

/**
 * @author mfh
 * @date 2020/7/25 19:46
 */
public class EveryTaskOneThread {
    public static void main(String[] args) {
        new Thread(new Task()).start();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("执行了线程");
        }
    }
}
