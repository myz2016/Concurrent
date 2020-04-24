package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.type_1;

/**
 * @author mfh
 * @date 2020/4/24 16:21
 */
public class Client {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread t0 = new Thread(new Producer(storage));
        Thread t1 = new Thread(new Consumer(storage));
        t0.start();
        t1.start();
    }
}
