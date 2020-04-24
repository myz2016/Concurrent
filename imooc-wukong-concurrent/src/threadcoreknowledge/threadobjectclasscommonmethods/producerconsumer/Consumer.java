package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer;

/**
 * @author mfh
 * @date 2020/4/24 16:35
 */
public class Consumer implements Runnable {
    private final Container<?> storage;

    public Consumer(Container<?> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.storage.take();
        }
    }
}
