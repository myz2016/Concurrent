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
    private int index;
    final static MultiThreadError multiThreadError = new MultiThreadError();
    private boolean[] marked = new boolean[100_00_00_00];
    private static AtomicInteger total = new AtomicInteger();
    private static AtomicInteger wrongCount = new AtomicInteger();
    private volatile CyclicBarrier cyclicBarrier0 = new CyclicBarrier(2);
    private volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            total.incrementAndGet();
            try {
                cyclicBarrier1.reset();
                cyclicBarrier0.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier0.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            synchronized (multiThreadError) {
                if (marked[index] && marked[index - 1]) {
                    wrongCount.incrementAndGet();
                    System.out.println("发生错误的数字：" + index);
                }
            }
            marked[index] = true;
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
        System.out.println("真正运行的次数：" + total.get() + " 次");
        System.out.println("错误：" + wrongCount.get() + " 次");
    }
}
