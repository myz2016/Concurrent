package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.support;

import java.util.Random;

/**
 * @author mfh
 * @date 2020/4/24 18:56
 */
public class Paper {
    private int length;
    private int width;

    public Paper() {
        Random random = new Random();
        this.length = random.nextInt(10);
        this.width = random.nextInt(5);
    }

    @Override
    public String toString() {
        return "Papper{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}
