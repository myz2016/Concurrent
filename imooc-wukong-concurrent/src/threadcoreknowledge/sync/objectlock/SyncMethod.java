package threadcoreknowledge.sync.objectlock;

/**
 * 同步方法
 * @author mfh
 * @date 2020/5/3 22:09
 */
public class SyncMethod implements Runnable {
    private static int num = 0;
    @Override
    public void run() {
        calculate();
    }

    public synchronized void calculate() {
        for (int i = 0; i < 1000_00; i++) {
            num++;
        }
    }
    public static void main(String[] args) {
        SyncMethod sd = new SyncMethod();
        Thread t0 = new Thread(sd);
        Thread t1 = new Thread(sd);
        t0.start();
        t1.start();
        /*t0.join();
        t1.join();*/
        while (t0.isAlive() || t1.isAlive()) {}
        System.out.println(num);
    }
}
