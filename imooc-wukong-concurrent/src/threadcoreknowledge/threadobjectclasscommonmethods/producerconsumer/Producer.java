package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer;

/**
 * 生产者
 * @author mfh
 * @date 2020/4/24 16:21
 */
public class Producer implements Runnable {
    private final Container<?> storage;

    public Producer(Container<?> storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.push();
        }
    }
}
