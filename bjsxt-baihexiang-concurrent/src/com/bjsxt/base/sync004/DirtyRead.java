package com.bjsxt.base.sync004;

public class DirtyRead {
    private String userName = "bjsxt";
    private String password = "123";

    public synchronized void setValue(String userName, String password) {
        try {
            this.userName = userName;
            Thread.sleep(3000);
            this.password = password;
            System.out.println(Thread.currentThread().getName() + ", userName：" + this.userName + ", password：" + this.password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getValue() {
        System.out.println(Thread.currentThread().getName() + ", userName：" + this.userName + ", password：" + password);
    }

    /**
     * 在我们对一个对象的方法加锁的时候，需要考虑业务的整体性，即为 setValue/getValue方法同时加锁 synchronized 同步关键字，保证
     * 业务（service）的原子性，不然会出现业务错误（也从侧面保证事务一致性）。也就是涉及到业务的方法，要么同时加锁，要么同时不加锁。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("z3", "456");
            }
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        dr.getValue();
    }
}
