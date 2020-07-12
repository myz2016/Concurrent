package threadcoreknowledge.jmm;

/**
 * 描述：演示可见性带来的问题
 * @author mfh
 * @date 2020/7/6 0:04
 */
public class FieldVisibility {
    private volatile int a = 1;
    private volatile int b = 2;
    private void change() {
        a = 3;
        b = a;
    }

    private void print() {
        System.out.printf("a：%d，b：%d\n", a, b);
    }
    
    public static void main(String[] args) {
        for (; ; ) {
            FieldVisibility fv = new FieldVisibility();
            Thread one = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fv.change();
            });

            Thread two = new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fv.print();
            });

            one.start();
            two.start();
        }
    }
}
