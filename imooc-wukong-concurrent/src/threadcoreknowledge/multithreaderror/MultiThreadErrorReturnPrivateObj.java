package threadcoreknowledge.multithreaderror;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 演示：返回的 private 对象被修改了
 * 解决方案：采用返回副本方式
 * @author mfh
 * @date 2020/5/10 23:20
 */
public class MultiThreadErrorReturnPrivateObj {
    private final Map<Integer, String> week;

    public MultiThreadErrorReturnPrivateObj() {
        week = new HashMap<>();
        week.put(1, "周一");
        week.put(2, "周二");
        week.put(3, "周三");
        week.put(4, "周四");
    }

    public Map<Integer, String> getWeek() {
        // 返回副本
        return new HashMap<>(week);
    }
    
    public static void main(String[] args) {
        Logger logger = Logger.getAnonymousLogger();
        MultiThreadErrorReturnPrivateObj m = new MultiThreadErrorReturnPrivateObj();
        Map<Integer, String> yesterday = m.getWeek();
        logger.info(yesterday.get(1));
        yesterday.remove(1);
        Map<Integer, String> today = m.getWeek();
        logger.info(today.get(1));
    }
}
