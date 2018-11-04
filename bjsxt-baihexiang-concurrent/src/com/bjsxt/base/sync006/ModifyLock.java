package com.bjsxt.base.sync006;

import java.util.concurrent.TimeUnit;

/**
 * 一个对象作为锁，对象里的属性发生变化，是不会影响锁的，除非是
 * 对象发生了变化，锁才会改变，就像ChangeLock里面的例子。
 */
public class ModifyLock {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private synchronized void changeAttribute(String name, int age) {
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
            this.setName(name);
            this.setAge(age);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "修改对象内容为：" + this.getName() + "," +this.getAge());
            Thread.sleep(2000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        final ModifyLock modifyLock = new ModifyLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * changeAttribute方法定义时使用了synchronized,哪个对象调用的此方法，就以那个对象作为锁对象。
                 * 比如这里就是以modifyLock作为锁；t2线程也是使用了modifyLock作为锁，所以t1和t2线程会存在锁
                 * 竞争的问题。
                 */
                modifyLock.changeAttribute("张三", 12);
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifyLock.changeAttribute("李四", 32);
            }
        }, "t2");

        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

}
