package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.type_1;

/**
 * @author mfh
 * @date 2020/4/24 16:35
 */
public class Consumer implements Runnable {
    private Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            this.storage.take();
        }
    }
}
