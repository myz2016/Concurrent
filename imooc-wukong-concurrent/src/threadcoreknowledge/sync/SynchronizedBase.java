package threadcoreknowledge.sync;

/**
 * 可重入粒度测试：调用父类的方法
 * @author mfh
 * @date 2020/5/4 9:45
 */
public class SynchronizedBase {
    public synchronized void method() {
        System.out.println("我是父类方法 method");
    }
}

class SynchronizedChild extends SynchronizedBase {
    public synchronized void method() {
        System.out.println("我是子类方法 method");
        super.method();
    }

    public static void main(String[] args) {
        SynchronizedBase sc = new SynchronizedChild();
        sc.method();
    }
}
