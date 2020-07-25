package threadcoreknowledge.sigleton;

/**
 * 描述：静态内部类
 *
 * @author mfh
 * @date 2020/7/18 17:29
 */
public class SingletonInnerClass {
    private SingletonInnerClass() {
    }

    public static SingletonInnerClass getInstance() {
        return SingletonHandler.INSTANCE;
    }

    private static class SingletonHandler {
        public static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }

}
