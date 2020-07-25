package threadcoreknowledge.sigleton;

/**
 * 描述：懒汉式（线程不安全） 不可用
 * @author mfh
 * @date 2020/7/18 15:42
 */
public class LazyManThreadUnsafe {
    private static LazyManThreadUnsafe INSTANCE;
    private LazyManThreadUnsafe(){}

    public static LazyManThreadUnsafe getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyManThreadUnsafe();
        }
        return INSTANCE;
    }
}
