package threadcoreknowledge.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 描述：演示重排序的现象 “直到达到某个条件才停止”，测试小概率事件
 * @author mfh
 * @date 2020/7/5 21:01
 */
public class OutOfOrderExecution {
    private static int x, y = 0;
    private static int a, b = 0;
    static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch cdl = new CountDownLatch(1);

            Thread one = new Thread(() -> {

                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread two = new Thread(() -> {
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            two.start();
            one.start();
            cdl.countDown();
            one.join();
            two.join();
            System.out.println("执行第 " + i + " 次");
            if (x == 0 && y == 0) {
                System.out.println("x = " +  x + ", y= " + y);
                break;
            } else {
                System.out.println("x = " +  x + ", y= " + y);
            }

        }
    }

}
