package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer;

import threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.support.Paper;

import java.util.LinkedList;

/**
 * @author mfh
 * @date 2020/4/24 16:22
 */
public class Box implements Container<Paper> {
    private int maxSize;
    private LinkedList<Paper> container;

    public Box() {
        this.maxSize = 10;
        this.container = new LinkedList<>();
    }

    public synchronized void push() {
        while (container.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        container.add(new Paper());
        System.out.println("生产产品，仓库中有了" + container.size() + "个产品！");
        notify();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized Paper take() {
        while (container.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Paper paper = container.poll();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("拿出了 " + paper + "，当前仓库中剩余 " + container.size() + " 个产品！");
        notify();
        return paper;
    }

    @Override
    public int getMaxSize() {
        return this.maxSize;
    }
}
