package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.type_1;

/**
 * 生产者
 * @author mfh
 * @date 2020/4/24 16:21
 */
public class Producer implements Runnable {
    private Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            storage.put();
        }
    }
}
