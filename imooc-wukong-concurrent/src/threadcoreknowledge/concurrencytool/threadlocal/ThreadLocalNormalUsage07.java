package threadcoreknowledge.concurrencytool.threadlocal;

/**
 * 描述：避免参数传递的麻烦
 * @author mfh
 * @date 2020/8/2 14:37
 */
public class ThreadLocalNormalUsage07 {
    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1 {
    public void process() {
        User user = new User("大师兄");
        ThreadLocalContextHolder.holder.set(user);
        new Service2().process();
    }
}

class ThreadLocalContextHolder {
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Service2 {
    public void process() {
        User user = ThreadLocalContextHolder.holder.get();
        System.out.println("Service2 拿到用户名：" + user.getName());
        new Service3().process();
    }
}

class Service3 {
    public void process() {
        User user = ThreadLocalContextHolder.holder.get();
        System.out.println("Service3 拿到用户名：" + user.getName());
        // 使用完之后要移除，防止内存泄露
        ThreadLocalContextHolder.holder.remove();
        System.out.println(ThreadLocalContextHolder.holder.get());
    }
}