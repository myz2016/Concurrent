package threadcoreknowledge.deadlock;

/**
 * @author mfh
 * @date 2020/7/19 11:22
 */
public class TransferMoney implements Runnable {
    Account z3 = new Account("张三", 500);
    Account l4 = new Account("李四", 500);
    public static void main(String[] args) throws InterruptedException {
        TransferMoney tm = new TransferMoney();
        Thread t0 = new Thread(tm, "t0");
        Thread t1 = new Thread(tm, "t1");
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        tm.printMoney();
    }

    private void printMoney() {
        System.out.printf("%s 账户余额：%d元%n%s 账户余额：%d元%n", z3.name, z3.balance, l4.name, l4.balance);
    }
    @Override
    public void run() {
        if ("t0".equals(Thread.currentThread().getName())) {
            try {
                transferMoney(z3, l4, 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                transferMoney(l4, z3, 200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

     static void transferMoney(Account from, Account to, int money) throws InterruptedException {
        synchronized (from) {
            // 此处休眠等待会造成死锁，反之不会死锁。
            Thread.sleep(500);
            synchronized (to) {
                System.out.printf("%s 转给 %s [%d 元]%n", from.name, to.name, money);
                if (from.balance - money < 0) {
                    System.out.printf("%s 账户余额不足，转账失败！%n", from.name);
                } else {
                    from.balance -= money;
                    to.balance += money;
                }
            }
        }
    }

    public static class Account {
        String name;
        int balance;
        public Account(String name, int balance) {
            this.name = name;
            this.balance = balance;
        }
    }
}
