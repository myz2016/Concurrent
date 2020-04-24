package threadcoreknowledge.threadobjectclasscommonmethods.producerconsumer.support;

/**
 * @author mfh
 * @date 2020/4/24 18:54
 */
public class Apple {
    private int num;
    private ColorEnum color;

    public Apple(int num, ColorEnum color) {
        this.num = num;
        this.color = color;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + num +
                ", color='" + color + '\'' +
                '}';
    }
}
