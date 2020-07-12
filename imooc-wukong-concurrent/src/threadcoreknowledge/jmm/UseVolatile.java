package threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：解释，被各个线程赋值。没有其他操作
 * @author mfh
 * @date 2020/7/12 16:28
 */
public class UseVolatile implements Runnable {
    volatile boolean done = false;
    AtomicInteger realA = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            this.setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        // done 的赋值操作不取决于之前的值
        this.done = true;
    }

    public static void main(String[] args) throws InterruptedException {
        UseVolatile nv = new UseVolatile();
        Thread t0 = new Thread(nv);
        Thread t1 = new Thread(nv);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.printf("done：%b\nrealA：%d", nv.done, nv.realA.get());
    }
}
