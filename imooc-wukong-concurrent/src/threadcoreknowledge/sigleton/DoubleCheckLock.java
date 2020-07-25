package threadcoreknowledge.sigleton;

/**
 * 描述：双重检查锁（推荐使用）
 * @author mfh
 * @date 2020/7/18 15:49
 */
public class DoubleCheckLock {
    private volatile static DoubleCheckLock INSTANCE;
    private DoubleCheckLock(){}

    public static DoubleCheckLock getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckLock.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckLock();
                }
            }
        }
        return INSTANCE;
    }
}
