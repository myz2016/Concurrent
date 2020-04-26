package threadcoreknowledge.threadobjectclasscommonmethods.wait;

/**
 * 证明 wait 只释放当前的那把锁
 * @author mfh
 * @date 2020/4/24 15:15
 */
public class WaitNotifyReleaseOwnMonitor {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " 获取到资源锁A！");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + " 获取到资源锁B！");
                    try {
                        System.out.println(Thread.currentThread().getName() + " 释放资源锁A！");
                        resourceA.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " 获取到资源锁A！");
                System.out.println(Thread.currentThread().getName() + " 尝试获取资源锁B！");
                synchronized (resourceB) {
                    // 以下代码不会执行
                    System.out.println(Thread.currentThread().getName() + " 获取到资源锁B！");
                }
            }
        }).start();
    }
}
