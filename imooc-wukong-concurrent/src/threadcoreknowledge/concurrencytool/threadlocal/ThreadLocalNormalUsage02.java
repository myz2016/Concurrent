package threadcoreknowledge.concurrencytool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：1000 个任务使用线程池执行
 * @author mfh
 * @date 2020/8/1 19:32
 */
public class ThreadLocalNormalUsage02 {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
