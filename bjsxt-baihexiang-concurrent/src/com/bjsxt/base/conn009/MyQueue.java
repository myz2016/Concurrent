package com.bjsxt.base.conn009;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
    private LinkedList<Object> list = new LinkedList<>();
    private AtomicInteger count = new AtomicInteger(0);
    private final int maxSize;
    private final int minSize = 0;
    private Object lock = new Object();
    public MyQueue(int size) {
        this.maxSize = size;
    }
    private int getSize() {
        return list.size();
    }

    public void put(Object obj) {
        synchronized (lock) {
            while(count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            System.out.println("元素" + obj + "加入队列");
            count.incrementAndGet();
            lock.notify();
        }
    }

    public Object take() {
        Object first;
        synchronized (lock) {
            while (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            first = list.removeFirst();
            count.decrementAndGet();
            lock.notify();
            System.out.println("元素" + first + "从队列中移除");
        }
        return first;
    }
    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue(5);
        myQueue.put("1");
        myQueue.put("2");
        myQueue.put("3");
        myQueue.put("4");
        myQueue.put("5");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myQueue.put("6");
                myQueue.put("7");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object take = myQueue.take();
                Object take1 = myQueue.take();
            }
        });

        t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
