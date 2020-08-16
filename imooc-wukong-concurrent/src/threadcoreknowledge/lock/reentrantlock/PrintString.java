package threadcoreknowledge.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mfh
 * @date 2020/8/9 12:04
 */
public class PrintString {
    public static void main(String[] args) {
        Outputer outputer = new Outputer();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                outputer.output("河北省");
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                outputer.output("邯郸市");
            }
        }).start();
    }

    static class Outputer {
        public void output(String name) {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println("");
        }
    }
}
