package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer;

import org.junit.Test;

/**
 * @author mfh
 * @date 2020/4/24 16:21
 */
public class Client {

    @Test
    public void testBasket() throws InterruptedException {
        Basket basket = new Basket();
        Thread t0 = new Thread(new Producer(basket));
        Thread t1 = new Thread(new Consumer(basket));
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }

    @Test
    public void testBox() throws InterruptedException {
        Box box = new Box();
        Thread t0 = new Thread(new Producer(box));
        Thread t1 = new Thread(new Consumer(box));
        t0.start();
        t1.start();
        t0.join();
        t1.join();
    }
}
