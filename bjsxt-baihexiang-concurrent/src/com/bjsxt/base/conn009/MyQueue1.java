package com.bjsxt.base.conn009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue1 {
    private LinkedList<Object> list = new LinkedList<>();
    private AtomicInteger ai = new AtomicInteger(0);
    private int maxSize;
    private int minSize;
    private Object lock = new Object();
    public MyQueue1(int maxSize) {
        this.maxSize = maxSize;
    }
    public void put(Object obj) {
        synchronized (lock) {
            while (this.ai.get() == this.maxSize) {
                try {
                    System.out.println(Thread.currentThread().getName() + "线程进入阻塞状态");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            ai.incrementAndGet();
            lock.notify();
            System.out.println("元素" + obj + "添加进容器");
        }
    }
    public Object take() {
        synchronized (lock) {
            Object first = null;
            while (this.ai.get() == this.minSize) {
                try {
                    System.out.println(Thread.currentThread().getName() + "线程进入阻塞状态");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            first = this.list.removeFirst();
            this.ai.decrementAndGet();
            lock.notify();
            System.out.println("元素" + first + "被取出");
            return first;
        }
    }
    public static void main(String[] args) {
       final MyQueue1 mq = new MyQueue1(5);
       mq.put("1");
       mq.put("2");
       mq.put("3");
       mq.put("4");
       mq.put("5");
       new Thread(new Runnable() {
           @Override
           public void run() {
               mq.put("6");
               mq.put("7");
           }
       }, "t1").start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                mq.take();
                mq.take();
                mq.take();
            }
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
