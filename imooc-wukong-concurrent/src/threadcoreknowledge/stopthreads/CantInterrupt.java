package threadcoreknowledge.stopthreads;

/**
 * @author mfh
 * @date 2020/4/14 21:20
 */
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.printf("%d是100的倍数\n", num);
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
    /**
     * while 内 try..catch，即使加入 Thread.currentThread().isInterrupted() 判断，循环也不会中断，抛出异常后会继续执行
     * 原因：java 设计 sleep 函数时，有这样一个理念，当它一旦响应中断，于是便会把线程的 interrupt 标记位清除，也就是说确实是
     * 在 sleep 过程中收到了中断，并且 catch 住了并打印了异常栈，但 sleep 设计的理念导致 interrupt 标记位会被清除，所以再次
     * 循环时，Thread.currentThread().isInterrupted() 判断就不起作用了，因为中断标记位已经被清除了，它检查不到任何被中断的
     * 迹象，导致程序不能退出。
     */
}
