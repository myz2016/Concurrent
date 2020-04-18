package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示用 volatile 的局限；part2 线程陷入阻塞，无法使用 volatile
 * 停止线程
 *
 * @author mfh
 * @date 2020/4/18 11:48
 */
public class WrongWayVolatileCantStop {
    public volatile boolean canceled = false;

    class Producer implements Runnable {
        private BlockingQueue<Integer> blockingQueue;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            int num = 1;
            try {
                while (num <= 100 && !canceled) {
                    blockingQueue.put(num);
                    System.out.printf("%d 被生产出来了\n", num);
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者停止生产");
            }
        }
    }

    class Consumer {
        private BlockingQueue<Integer> blockingQueue;

        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        void consume() {
            try {
                while (Math.random() <= 0.95) {
                    Integer take = blockingQueue.take();
                    System.out.printf("%d 被消费了\n", take);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者不需要更多的数据了");
            canceled = true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        WrongWayVolatileCantStop wwvcs = new WrongWayVolatileCantStop();
        Producer producer = wwvcs.new Producer(queue);
        Consumer consumer = wwvcs.new Consumer(queue);
        Thread t = new Thread(producer);
        t.start();
        Thread.sleep(1000);
        consumer.consume();
    }
}
