package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.support;

import java.util.Random;

/**
 * @author mfh
 * @date 2020/4/24 19:04
 */
public class RandomColorSelector {
    public static final ColorEnum selectColor() {
        ColorEnum[] values = ColorEnum.values();
        int length = values.length;
        Random random = new Random();
        return values[random.nextInt(length)];
    }
}
