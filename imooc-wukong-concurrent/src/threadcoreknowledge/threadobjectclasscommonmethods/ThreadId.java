package threadcoreknowledge.threadobjectclasscommonmethods;

/**
 * 描述：主线程 id 是 1，jvm 运行起来后，我们自己创建的线程的 id 早已不是 2
 * @author mfh
 * @date 2020/5/3 18:22
 */
public class ThreadId {
    public static void main(String[] args) {
        Thread t = new Thread();
        System.out.println("main id:" + Thread.currentThread().getId());
        System.out.println("t id:" + t.getId());
    }
}
