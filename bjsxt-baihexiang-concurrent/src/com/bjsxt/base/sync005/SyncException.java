package com.bjsxt.base.sync005;

import sun.org.mozilla.javascript.internal.ast.Yield;
import sun.plugin2.main.client.MessagePassingOneWayJSObject;

public class SyncException {
    private int i = 0;
    private synchronized void opration() {
        while(true) {
            try {
                i++;
                Thread.sleep(200);
                System.out.println(Thread.currentThread().getName() + ", i = " + i);
                if(i == 10) {
                    Integer.parseInt("a");
                }
            } catch (Exception e) { //InterruptedException
                /**
                 * 抛出异常以后，锁就会被释放，那么其他的线程就都会竞争锁
                 * 所以在捕获异常这里，要根据你自己的业务作出相应的处理。
                 */
                e.printStackTrace();
                System.out.println("log info i = " + i);
//                throw new RuntimeException();
                continue;
            }
        }
    }
}
