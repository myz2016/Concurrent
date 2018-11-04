package com.bjsxt.base.coll012;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Tickets {
    public static void main(String[] args) {
        final Vector<String> tickets = new Vector<>();
//        HashTable是线程安全的，如果不想用HashTable，而是想用HashMap,那么就使用下面的方式，返回的map就是线程安全的
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        for (int i = 0; i < 100; i++) {
            tickets.add(String.valueOf(i));
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if(tickets.isEmpty()) break;
                        System.out.println(Thread.currentThread().getName() + ":" + tickets.remove(0));
                    }
                }
            }, "线程：" + i).start();
        }
    }
    /**
     * 通过观察运行结果，即使在多线程的情况下，也没有重复移除票的情况。但是性能就不是很好了，因为都是加synchronized锁的。
     * 通过加锁，在同一个时间，只有一个线程可以访问容器中的元素，这时同步类容器。这种容器现在已经不能满足互联网高并发这种
     * 需求，就是既要线程安全，又要性能好，所以要使用并发类容器。jdk1.5以后提供了并发类容器。同步类容器都是串行化执行的，
     * 虽然线程安全，但是效率低。吞吐量降低，原本一秒钟可以访问1000次，但是使用并发类容器，1秒钟可以访问10000次左右。
     */
}
