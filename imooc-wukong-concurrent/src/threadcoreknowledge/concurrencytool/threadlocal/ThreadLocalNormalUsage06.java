package threadcoreknowledge.concurrencytool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mfh
 * @date 2020/8/2 14:01
 */
public class ThreadLocalNormalUsage06 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> System.out.println(date(finalI)));
        }
        threadPool.shutdown();
    }
    public static String date(int seconds) {
        // 参数单位是毫秒，从 1970-01-01 00:00:00 GMT计时
        Date date = new Date(seconds * 1000);
        String time;
        time = ThreadLocalHolder.tl.get().format(date);
        return time;
    }

    static class ThreadLocalHolder {
        static ThreadLocal<SimpleDateFormat> tl = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
