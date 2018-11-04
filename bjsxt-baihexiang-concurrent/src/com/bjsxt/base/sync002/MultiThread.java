package com.bjsxt.base.sync002;

/**
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 * 所以代码中哪个线程先执行synchronized关键字方法，哪个对象就先持有了该方法所属对象的锁（lock）.
 *
 * 在静态方法上加synchronized关键字，表示锁定，class类，类一级别的锁（独占，class类）
 */
public class MultiThread {
    /**
     * printNum 没有被 static 修饰时， num 变量也不需要被 static 修饰。
     * printNum 使用 static 修饰时， num 变量也被 static 修饰。
     */
    private static int num = 0;

    /** static */
    private static synchronized void printNum(String tag) {
        try {
            if("a".equals(tag)) {
                num = 100;
                System.out.println("tag a, set num over");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tag b, set num over");
            }
            System.out.println("tag " + tag + ", num " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //注意观察 run 方法输出顺序
    public static void main(String[] args) {
        //俩不同的对象
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * printNum 方法没有 static 修饰的情况：
                 *   1、m1 调用 printNum 方法，由于 printNum 是由 synchronized 修饰的，那么 synchronized 锁定的就是 m1 这个对象。
                 *   如果还存在其他线程 t99，并且其他线程访问的也是 m1 对象的 printNum 方法，此时存在 t99 与 t1 竞争锁的问题。
                 *   2、t1线程访问m1对象的printNum方法，t2线程访问m2对象的printNum方法。虽然 printNum 方法被 synchronized 关键字
                 *   修饰，但锁定的却不是一个对象，一个是 m1,一个是 m2，所以 t1 与 t2 线程之间不存在锁竞争问题。
                 *   3、打印结果
                 *      1> tag a, set num over
                 *      2> tag b, set num over
                 *      3> .....
                 *      分析：由于不存在锁竞争问题，所以 1> 和 2> 是并行执行的，会同时打印，不存在等待的情况。
                 *
                 * printNum 方法加入 static 修饰的情况：
                 *   1、当在一个 synchronized 修饰的方法上加入 static 关键字后，那么此方法持有的锁对象就不在是一个对象锁了，而变成
                 *   了当前类的类锁。这时，此类无论 new 多少个对象，只要以并发的形式访问此方法时，就需要竞争锁。因为这些对象都是属于
                 *   这个类的，而这个方法又是类级别的锁，所以多个对象同时访问此方法时会存在锁竞争问题。
                 *   2、打印结果
                 *      1> tag a, set num over
                 *      2> tag b, set num over
                 *      3> ....
                 *      分析：由于存在锁竞争问题， 所以 1> 与 2> 不是并行执行的，所以一旦有对象先获取到了类锁，那么其他对象需要排队
                 *      等待，等到 1 秒钟以后，休眠结束，释放锁后，其他线程才能再次竞争锁。所以先打印 1>，等一秒后，才会打印 2>
                 */
                m1.printNum("a");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m2.printNum("b");
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
