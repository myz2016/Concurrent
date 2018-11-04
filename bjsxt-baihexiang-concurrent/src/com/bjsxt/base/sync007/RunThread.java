package com.bjsxt.base.sync007;

public class RunThread extends Thread {
    /** volatile */
    private boolean isRunning = true;
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }
    @Override
    public void run() {
        while (isRunning) {

        }
        System.out.println("线程停止");
    }
    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
       /* RunThread rt1 = new RunThread();
        rt1.start();*/
        rt.start();
        Thread.sleep(3000);
        rt.setRunning(false);
        System.out.println("isRunning的值被设置为false");
        Thread.sleep(1000);
        System.out.println(rt.isRunning);

    }
    /**
     * volatile 比较适合用在实时读、实时读取被更新的数值这种操作会比较合适。
     * 不适合于写操作，比如一个线程 t1 拿到了一个全局变量10去执行业务操作，然后
     * 有另外一个线程 t2 将这个变量变成了15，虽然 t1 可以在第一时间知道这个值
     * 改变了，但是使用10这个数操作业务已经有一会了，你现在改成15必定会对 t1 造成
     * 影响。所以这时就不适合用 volatile 了，也可以说 volatile 并不具备同步性（也
     * 就是原子性）。什么叫同步性？简单来说就是一个线程在操作一个变量的时候，其他的线程
     * 并不能操作此变量，你得等着我操作完了，你才可以操作。
     */
    /**
     * 在java中，每一个线程都会有一块工作内存区，其中存放着所有线程共享的主内存中的变量值的拷贝。
     * 当线程执行时，它在自己的内存工作区中操作这些变量。为了存取一个共享的变量，一个线程通常先
     * 获取锁定并去清除它的内存工作区，把这些共享变量从所有线程的共享内存区中正确的装入到它自己
     * 所在的工作内存区中，当线程解锁时保证该工作内存区中变量的值回写到共享内存中。
     * 一个线程可以的操作有使用（use）、赋值（assign）、装载（load）、存储（store）、锁定（lock）、解锁（unlock）
     * 而主内存可以执行的操作有读（read）、写（write）、锁定（lock）、解锁（unlock）,每个操作都是原子的 。
     * volatile的作用就是强制线程到主内存（共享内存）里去读取变量，而不去线程工作内存区里去读取，从而实现
     * 多个线程间的变量可见。也就是满足线程安全的可见性。
     */
    /**
     * 使用 volatile 关键字修饰变量，并且满足线程安全的可见性，这里指的是当只有一个线程的时候，比如再创建一个实例对象
     *     RunThread rt1 = new RunThread();
     *     rt1.start();
     * 这时 rt1 对象中也会有一个 isRunning 变量，这时如果改变 rt 对象中的 isRunning 变量的值，即使此变量被 volatile 关键字
     * 修饰，那么也不会影响到 rt1 中的 isRunning 变量。但如果是 MyRunThread 类中描述的那样，volatile 修饰的变量还是起作用的。
     */
}
