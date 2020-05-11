package threadcoreknowledge.multithreaderror;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 演示：在构造函数中开启新的线程，这样做也会有问题。
 * @author mfh
 * @date 2020/5/10 23:20
 */
public class MultiThreadErrorNewThreadInConstructor {
    private Map<Integer, String> week;

    public MultiThreadErrorNewThreadInConstructor() {
        new Thread(() -> {
            week = new HashMap<>();
            week.put(1, "周一");
            week.put(2, "周二");
            week.put(3, "周三");
            week.put(4, "周四");
        }).start();
    }

    public Map<Integer, String> getWeek() {
        // 返回副本
        return new HashMap<>(week);
    }
    
    public static void main(String[] args) throws InterruptedException {
        Logger logger = Logger.getAnonymousLogger();
        MultiThreadErrorNewThreadInConstructor m = new MultiThreadErrorNewThreadInConstructor();
        Thread.sleep(100);
        Map<Integer, String> yesterday = m.getWeek();
        logger.info(yesterday.get(1));
        yesterday.remove(1);
        Map<Integer, String> today = m.getWeek();
        logger.info(today.get(1));
    }
}
