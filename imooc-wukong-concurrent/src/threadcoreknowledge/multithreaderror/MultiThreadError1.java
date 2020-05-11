package threadcoreknowledge.multithreaderror;

/**
 * 演示：在构造函数中未初始化完毕就给 this 赋值
 * 导致的问题：
 * Thread.sleep(10); 输出的 point:x=1,y=0
 * Thread.sleep(105); 输出的 point:x=1,y=1
 * 这样的结果是不稳定的
 * @author mfh
 * @date 2020/5/10 23:34
 */
public class MultiThreadError1 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        // 休眠10毫秒
        Thread.sleep(10);
        System.out.println(point);
    }
}

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        MultiThreadError1.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class PointMaker extends Thread {
    @Override
    public void run() {
        try {
            new Point(1,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
