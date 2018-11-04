package com.bjsxt.base.conn010;

public class ConnThreadLocal {
    private ThreadLocal<String> tl = new ThreadLocal<>();
    public void setValue(String value) {
        tl.set(value);
    }
    public void getValue() {
        System.out.println(Thread.currentThread().getName() + ":" + this.tl.get());
    }
    public static void main(String[] args) {
        final ConnThreadLocal ct = new ConnThreadLocal();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ct.setValue("张三");
                ct.getValue();
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ct.getValue();
            }
        },"t2").start();
    }
}
