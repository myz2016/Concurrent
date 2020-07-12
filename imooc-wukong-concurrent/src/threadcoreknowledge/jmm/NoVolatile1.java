package threadcoreknowledge.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：boolean 类型共享变量的其他操作
 * 因为 done 的赋值操作依赖之前的值，此操作非原子性操作，所以即使使用了
 * volatile 修饰 boolean 类型的共享变量，也不能保证线程安全
 * @author mfh
 * @date 2020/7/12 15:52
 */
public class NoVolatile1 implements Runnable {
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
        // done 的赋值操作依赖之前的值，此操作非原子性操作
        this.done = !done;
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
