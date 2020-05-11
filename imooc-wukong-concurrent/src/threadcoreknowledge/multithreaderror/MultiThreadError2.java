package threadcoreknowledge.multithreaderror;

import java.util.concurrent.TimeUnit;

/**
 * 描述：观察者模式
 *
 * @author mfh
 * @date 2020/5/11 21:35
 */
public class MultiThreadError2 {
    int count;

    public MultiThreadError2(MySource mySource) {
        mySource.registerListener((e) -> System.out.printf("\n我得到的数字是%d\n", count));
        for (int i = 0; i < 1000; i++) {
            System.out.print(i);
        }
        count = 100;
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
        MultiThreadError2 me2 = new MultiThreadError2(source);
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
