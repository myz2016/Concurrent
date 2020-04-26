package threadcoreknowledge.threadobjectclasscommonmethods.sleep;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @author mfh
 * @date 2020/4/26 14:21
 */
public class SleepInterrupted implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(LocalTime.now());
            } catch (InterruptedException e) {
                System.out.println("我被中断了！");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SleepInterrupted sleepInterrupted = new SleepInterrupted();
        Thread t = new Thread(sleepInterrupted);
        t.start();
        Thread.sleep(6500);
        t.interrupt();
    }
}
