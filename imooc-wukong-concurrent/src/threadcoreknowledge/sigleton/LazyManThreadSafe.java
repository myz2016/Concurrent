package threadcoreknowledge.sigleton;

/**
 * 描述：懒汉式单例（线程安全） 不推荐使用
 * @author mfh
 * @date 2020/7/18 15:49
 */
public class LazyManThreadSafe {
    private static LazyManThreadSafe INSTANCE;
    private LazyManThreadSafe(){}

    public synchronized static LazyManThreadSafe getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LazyManThreadSafe();
        }
        return INSTANCE;
    }
}
