package threadcoreknowledge.threadobjectclasscommonmethods.analysisthreadstate;

/**
 * m1 与 m2 的差别是 m2 在输出线程状态前
 * 休眠了 20ms，正是由于这 20ms，打印出
 * 的效果是不一样的
 * @author mfh
 * @date 2020/4/19 22:15
 */
public class Wait {
    public final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Wait w = new Wait();
//        w.m1();
        w.m2();
    }

    private void m1() throws InterruptedException {
        Thread t0 = new Thread(new ThreadA(this.lock));
        Thread t1 = new Thread(new ThreadB(this.lock));
        t0.start();
        Thread.sleep(200);
        t1.start();
        System.out.println("T0:" + t0.getState());
        System.out.println("T1:" + t1.getState());
        System.out.println("T0:" + t0.getState());
    }
    private void m2() throws InterruptedException {
        Thread t0 = new Thread(new ThreadA(this.lock));
        Thread t1 = new Thread(new ThreadB(this.lock));
        t0.start();
        Thread.sleep(200);
        t1.start();
        // 加上下面这句话与不加下面这话，T0 的状态是不一样的，加上后 T0:BLOCKED；不加 T0:WAITING
        Thread.sleep(20);
        System.out.println("T0:" + t0.getState());
        System.out.println("T1:" + t1.getState());
    }
}
