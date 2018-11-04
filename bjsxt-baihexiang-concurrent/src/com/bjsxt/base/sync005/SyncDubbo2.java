package com.bjsxt.base.sync005;

/**
 * synchronized的重入
 * synchronize关键字拥有重入的功能，也就是在使用synchronize时，当一
 * 个线程得到一个对象的锁后，再次请求此对象时可以再次得到该对象的锁。
 * 下面的代码是没有问题的
 */
public class SyncDubbo2 {

    static class Base {
        protected int i = 10;
        protected synchronized void oprationSup() {
            try {
                i--;
                System.out.println("Base print i = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Base {
        protected synchronized void oprationSub() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println("Sub print i = " + i);
                    Thread.sleep(100);
                    super.oprationSup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Sub sub = new Sub();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sub.oprationSub();
            }
        }, "t1").start();
    }
}
