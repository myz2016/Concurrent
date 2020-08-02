package threadcoreknowledge.concurrencytool.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：两个线程打印日期没有什么问题
 * @author mfh
 * @date 2020/8/1 19:32
 */
public class ThreadLocalNormalUsage00 {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(date(10));
        }).start();

        new Thread(() -> {
            System.out.println(date(1007));
        }).start();
    }
    public static String date(int seconds) {
        // 参数单位是毫秒，从 1970-01-01 00:00:00 GMT计时
        Date date = new Date(seconds * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
