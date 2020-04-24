package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer;

import threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.support.Apple;
import threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.support.RandomColorSelector;

/**
 * @author mfh
 * @date 2020/4/24 18:41
 */
public class Basket implements Container<Apple> {
    private int maxSize;
    private int index = -1;
    private Apple[] storage;
    public Basket() {
        this.maxSize = 10;
        storage = new Apple[this.maxSize];
    }

    @Override
    public synchronized void push() {
        while (index + 1 == this.getMaxSize()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        index++;
        Apple apple = new Apple(index, RandomColorSelector.selectColor());
        storage[index] = apple;
        System.out.printf("生产苹果：%s，当前苹果数量：%d个！\n", apple, index + 1);
        notify();
    }

    @Override
    public synchronized Apple take() {
        while (index == -1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Apple apple = storage[index];
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        index--;
        System.out.printf("消费苹果：%s，当前苹果数量：%d个！\n", apple, index + 1);
        notify();
        return apple;
    }

    @Override
    public synchronized int getMaxSize() {
        return this.maxSize;
    }
}
