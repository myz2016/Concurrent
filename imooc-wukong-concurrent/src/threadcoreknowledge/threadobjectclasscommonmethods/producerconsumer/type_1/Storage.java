package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.type_1;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * @author mfh
 * @date 2020/4/24 16:22
 */
public class Storage {
    private int maxSize;
    private LinkedList<LocalDate> container;

    public Storage() {
        this.maxSize = 10;
        this.container = new LinkedList<>();
    }

    public synchronized void put() {
        while (container.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        container.add(LocalDate.now());
        System.out.println("生产产品，仓库中有了" + container.size() + "个产品！");
        notify();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void take() {
        while (container.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("拿出了 " + container.poll() + " 产品，当前仓库中剩余 " + container.size() + " 个产品！");
        notify();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
