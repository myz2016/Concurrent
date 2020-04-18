package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * 演示用 volatile 的局限：part1 看似可行
 * @author mfh
 * @date 2020/4/18 11:38
 */
public class WrongWayVolatile implements Runnable {
    private volatile boolean canceled = false;
    @Override
    public void run() {
        int num = 1;
        while (num <= 100 * 10 && !canceled) {
            if (num % 100 == 0) {
                System.out.printf("%d 是100的倍数\n", num);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread t1 = new Thread(r);
        t1.start();
        Thread.sleep(1000);
        r.canceled = true;
    }
}
