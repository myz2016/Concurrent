package threadcoreknowledge.sync;

import java.util.logging.Logger;

/**
 * 描述：synchronized 不仅保证了原子性，还保证了可见性，近朱者赤
 *
 * @author mfh
 * @date 2020/7/12 19:46
 */
public class FieldVisibility {
    private int a = 6;
    private int b = 6;
    private int c = 6;
    private int d = 6;

    public void change() {
        a = 2;
        b = 3;
        c = 4;
        synchronized (this) {
            d = 5;
        }
    }

    public void print() {
        Logger logger = Logger.getAnonymousLogger();
        int aa;
        synchronized (this) {
            aa = a;
        }
        int bb = b;
        int cc = c;
        int dd = d;
        logger.info(String.format("%naa:%d%nbb:%d%ncc:%d%ndd:%d%n", aa, bb, cc, dd));
    }

    public static void main(String[] args) throws InterruptedException {
        FieldVisibility fv = new FieldVisibility();
        Thread t0 = new Thread(() -> fv.change());

        Thread t1 = new Thread(() -> fv.print());

        t0.start();
        Thread.sleep(5);
        t1.start();

        t0.join();
        t1.join();
    }

}
