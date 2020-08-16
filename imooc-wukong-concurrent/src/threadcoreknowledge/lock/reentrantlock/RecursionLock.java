package threadcoreknowledge.lock.reentrantlock;

import java.sql.ResultSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：锁的递归调用
 * @author mfh
 * @date 2020/8/9 15:41
 */
public class RecursionLock {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        RecursionLock.accessResource();
    }
    private static void accessResource() {
        reentrantLock.lock();
        try {
            System.out.println("已经对资源进行了处理");
            System.out.println("第 " + reentrantLock.getHoldCount() + " 次获取到了锁");
            if (reentrantLock.getHoldCount() < 5) {
                // 递归
                accessResource();
            }
        } finally {
            reentrantLock.unlock();
            System.out.println("持有锁次数：" + reentrantLock.getHoldCount() + " 次");
        }
    }
}