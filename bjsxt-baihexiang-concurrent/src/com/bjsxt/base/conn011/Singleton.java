package com.bjsxt.base.conn011;

/**
 * 跟随类一起初始化，天然就是一个线程安全的，这是一种使用内部静态类的方式保证线程安全的方法。
 */
public class Singleton {
    private static class InnerSingleton {
        private static Singleton sl = new Singleton();
    }
    public static Singleton getInstance() {
        return InnerSingleton.sl;
    }
}
