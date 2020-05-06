package threadcoreknowledge.multithreaderror;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 运行结果出错
 * 演示计数不准确（减少），找出出错的位置。
 *
 * @author mfh
 * @date 2020/5/5 11:27
 */
public class MultiThreadError implements Runnable {
    final static MultiThreadError multiThreadError = new MultiThreadError();
    int index;
    boolean[] marked = new boolean[1000000];
    /* 实际运行次数 */
    static AtomicInteger total = new AtomicInteger();
    /* 错误次数 */
    static AtomicInteger wrongCount = new AtomicInteger();
    CyclicBarrier cyclicBarrier0 = new CyclicBarrier(2);
    CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    @Override
    public void run() {
        /** 最开始先把下标 0 的位置赋值为 true */
        marked[index] = true;
        for (int i = 0; i < 10000; i++) {
            total.incrementAndGet();
            try {
                cyclicBarrier1.reset();
                /** 等待 t0、t1 都到位了才开始执行 */
                cyclicBarrier0.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier0.reset();
                /** 等到都执行完了 index++ 才开始执行 */
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            synchronized (multiThreadError) {
                /** 不光判断当前位置，还有判断前一个位置 */
                if (marked[index] && marked[index - 1]) {
                    wrongCount.incrementAndGet();
                    System.out.println("线程冲突，错误 index：" + index);
                }
                /**
                 *  当某一个线程将此索引标记为 true，如果正常的情况，下一个线程进来应该执行 index++，
                 *  if 判断肯定为 false，因为正常情况下，赋值 true 是在 if 下面，如果上面的 if
                 *  条件成立，则说明两个线程同时操作了 index
                 */
                marked[index] = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(multiThreadError);
        Thread t1 = new Thread(multiThreadError);
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        System.out.println("表面上的结果，num：" + multiThreadError.index);
        System.out.println("一共实际执行的次数：" + total.get());
        System.out.println("发生错误次数：" + wrongCount.get());
    }
}
