package threadcoreknowledge.uncaughtexception.bestpractice;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自定义的异常处理器
 * @author mfh
 * @date 2020/5/3 20:44
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING, "线程异常，终止了：" + t.getName() + ", 异常信息：" + e);
        System.out.println(this.name + "捕获了异常：" + t.getName() + " 发生的异常");
    }
}
