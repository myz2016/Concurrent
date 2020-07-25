package threadcoreknowledge.deadlock;

import java.util.Random;
import java.util.UUID;

/**
 * @author mfh
 * @date 2020/7/19 12:54
 */
public class MultiTransferMoney {
    private static final int NUM_ACCOUNT = 500;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATIONS = 10_00_00;
    private static final int NUM_THREADS = 20;
    public static void main(String[] args) {
        Random random = new Random();
        TransferMoney.Account[] accounts = new TransferMoney.Account[NUM_ACCOUNT];
        for (int i = 0; i < NUM_ACCOUNT; i++) {
            accounts[i] = new TransferMoney.Account(UUID.randomUUID().toString(), NUM_MONEY);
        }
        class TransferThread implements Runnable {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    try {
                        TransferMoney.transferMoney(accounts[random.nextInt(NUM_ACCOUNT)], accounts[random.nextInt(NUM_ACCOUNT)], random.nextInt(NUM_MONEY));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(new TransferThread()).start();
        }
    }
}
