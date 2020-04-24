package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer;

/**
 * @author mfh
 * @date 2020/4/24 18:39
 */
public interface Container<T> {
    void push();
    T take();
    int getMaxSize();
}
