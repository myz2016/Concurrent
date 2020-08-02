package threadcoreknowledge.concurrencytool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：利用 ThreadLocal。给每个线程分配自己的 dateFormat 对象，保证了线程安全，高效利用了内存
 * @author mfh
 * @date 2020/8/1 19:32
 */
public class ThreadLocalNormalUsage05 {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    static ThreadLocal<SimpleDateFormat> tl = ThreadLocalHolder.tl;
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
        time = tl.get().format(date);
        return time;
    }
}

class ThreadLocalHolder {
    public static ThreadLocal<SimpleDateFormat> tl = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}