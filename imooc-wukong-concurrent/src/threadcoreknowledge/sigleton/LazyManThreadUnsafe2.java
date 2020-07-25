package threadcoreknowledge.sigleton;

/**
 * 描述：懒汉式单例（不线程安全） 不推荐使用
 * @author mfh
 * @date 2020/7/18 15:49
 */
public class LazyManThreadUnsafe2 {
    private static LazyManThreadUnsafe2 INSTANCE;
    private LazyManThreadUnsafe2(){}

    public static LazyManThreadUnsafe2 getInstance() {
        if (INSTANCE == null) {
            synchronized (LazyManThreadUnsafe2.class) {
                INSTANCE = new LazyManThreadUnsafe2();
            }
        }
        return INSTANCE;
    }
}
