package threadcoreknowledge.multithreaderror;

import java.util.concurrent.TimeUnit;

/**
 * 描述：使用工厂模式修复 MultiThreadError2 的问题
 * 思路：
 * 1.构造私有化
 * 2.使用方法去创建对象，使得构造函数完全结束后，再返回对象
 * @author mfh
 * @date 2020/5/11 21:35
 */
public class MultiThreadError2FixByFactoryPattern {
    int count;
    private EventListener listener;
    private MultiThreadError2FixByFactoryPattern() {
        listener = (e) -> System.out.printf("\n我得到的数字是%d\n", count);
        for (int i = 0; i < 1000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static MultiThreadError2FixByFactoryPattern getInstance(MySource source) {
        MultiThreadError2FixByFactoryPattern fix = new MultiThreadError2FixByFactoryPattern();
        source.registerListener(fix.listener);
        return fix;
    }

    public static void main(String[] args) {
        MySource source = new MySource();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            source.eventCome(new Event() {
            });

        }).start();
        MultiThreadError2FixByFactoryPattern.getInstance(source);
    }

    static class MySource {
        private EventListener listener;

        void registerListener(EventListener listener) {
            this.listener = listener;
        }

        void eventCome(Event event) {
            if (listener != null) {
                listener.onEvent(event);
            } else {
                System.out.println("还未初始化完毕");
            }
        }
    }

    interface EventListener {
        void onEvent(Event event);
    }

    interface Event {
    }
}
