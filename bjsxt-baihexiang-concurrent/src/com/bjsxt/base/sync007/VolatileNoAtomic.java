package com.bjsxt.base.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Volatile 关键字虽然拥有多个线程之间的可见性，但是却不具备同步性（也就是原子性），可以算的上是一个轻量级的synchronized,
 * 性能要比 synchronized 强很多，不会造成阻塞（在很多开源框架里，比如 netty 的底层代码就大量使用了 volatile）.
 * 这里需要注意:一般 volatile 用于只针对多个线程可见的变量操作，并不能代替 synchronized 的同步功能。
 */
public class VolatileNoAtomic extends Thread {
//    private static volatile int count;
    private static AtomicInteger count = new AtomicInteger(0);
    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
//            count++;
            count.incrementAndGet();
        }
        System.out.println(count);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] vnas = new VolatileNoAtomic[10];
        for (int i = 0; i < 10; i++) {
            vnas[i] = new VolatileNoAtomic();
        }
        for (int i = 0; i < 10; i++) {
            vnas[i].start();
        }
    }
    /**
     * 一共十个线程，一个线程加1000，所以最后肯定是10000
     * 但是如果使用 static volatile int count, 最后结果
     * 不一定是10000，但是也有肯定是10000。但是肯定会有不
     * 是的时候，比如 1000
                     7262
                     8262
                     6262
                     5262
                     5160
                     4300
                     3000
                     2000
                     9262
      但是使用 static AtomicInteger count = new AtomicInteger(0)
      最后的结果一定是10000，无论中间的过程如何，最后结果一定是对的。
      使用AtomicInteger后的结果： 2948
                                 8834
                                 8378
                                 7815
                                 7682
                                 6871
                                 4789
                                 9849
                                 4474
                                 10000
      可以看到，最后的结果一定是10000，无论执行多少次。但是中间的
      过程为什么不适1000,2000这种整数的呢？有可能是因为当一个线程
      执行完1000次的加和之后，还没有来得及打印，下一个线程立马又对
      count进行了加和操作，当上一个线程打印的时候，count的值早就不
      是1000了，多个线程执行的速度是很快的。但是无论中间的过程如何
      最后的结果一定是10000。但是不适用AotimicIntger的时候就不一定
      了。
     */
}
