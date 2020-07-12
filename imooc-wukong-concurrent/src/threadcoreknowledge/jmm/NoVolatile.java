package threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：不适用于 volatile 场景
 * @author mfh
 * @date 2020/7/12 15:52
 */
public class NoVolatile implements Runnable {
    volatile int a;
    AtomicInteger realA = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        NoVolatile nv = new NoVolatile();
        Thread t0 = new Thread(nv);
        Thread t1 = new Thread(nv);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.printf("a：%d\nrealA：%d", nv.a, nv.realA.get());
    }
}
