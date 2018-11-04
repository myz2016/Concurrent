package com.bjsxt.base.sync007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicUse {
    private static AtomicInteger ai = new AtomicInteger(0);
    //多个addAndGet在一个方法内是非原子性的，需要加synchronized进行修饰，保证4个addAndGet整体原子性
    /** synchronized */
    private synchronized int multiAdd() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /** ai.addAndGet只能保证addAndGet这个方法执行的原子性，但是由于mltiAdd方法并没有加synchronized关键字修饰，所以并不能保证
         *  ai.addAndGet(1),ai.addAndGet(2),ai.addAndGet(3),ai.addAndGet(4)这四个方法共同的原子性*/
        ai.addAndGet(1  );
        ai.addAndGet(2  );
        ai.addAndGet(3  );
        ai.addAndGet(4  );
        return ai.get();
    }
    public static void main(final String[] args) {
        final AtomicUse au = new AtomicUse();
        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(au.multiAdd());
                }
            });
            list.add(t);
        }
        for(Thread thread : list) {
            thread.start();
        }
    }
    /**
     *   20
         20
         30
         40
         50
         100
         90
         71
         60
         80
         110
         120
         206
         181
         190
         170
         160
         150
         130
         140
         250
         246
         230
         220
         210
         260
         340
         320
         330
         310
         300
         290
         280
         270
         370
         350
         360
         381
         500
         490
         480
         470
         460
         450
         440
         430
         420
         580
         410
         690
         736
         780
         820
         870
         910
         960
         393
         400
         1000
         990
         970
         980
         940
         950
         920
         930
         900
         880
         890
         860
         850
         840
         830
         810
         801
         790
         760
         770
         750
         740
         720
         700
         710
         680
         660
         670
         650
         640
         630
         620
         600
         610
         590
         570
         560
         550
         540
         520
         530
         511
     multiAdd方法每次都是加10，打印一下结果，每次应该都是10、20、30...,由于System.out.println打印的不是那么精确，不一定是按照顺序打印的，有可能是10、30、40..
     这些都没有关系，但是怎么样就有问题了呢？如果说在结果中见到打印出的数字不是10的整数倍的数字，比如511、801。这就证明了multiAdd方法并没有保证绝对的原子性。
     你可能有一个线程在 ai.addAndGet(2) 方法执行的时候，也有其他线程进入了multiAdd方法做了一些事情，这样就会出现511、801这样的数字。
     如果在multiAdd方法上加上synchronize关键字修饰，那么无论方法如何执行，打印出的顺序永远是下面这样的：
         10
         20
         30
         40
         50
         60
         70
         80
         90
         100
         110
         120
         130
         140
         150
         160
         170
         180
         190
         200
         210
         220
         230
         240
         250
         260
         270
         280
         290
         300
         310
         320
         330
         340
         350
         360
         370
         380
         390
         400
         410
         420
         430
         440
         450
         460
         470
         480
         490
         500
         510
         520
         530
         540
         550
         560
         570
         580
         590
         600
         610
         620
         630
         640
         650
         660
         670
         680
         690
         700
         710
         720
         730
         740
         750
         760
         770
         780
         790
         800
         810
         820
         830
         840
         850
         860
         870
         880
         890
         900
         910
         920
         930
         940
         950
         960
         970
         980
         990
         1000
     multiAdd方法加入了synchronized关键字后，就会保证4个addAndGet方法的原子性。所以打印出来的都是10的整数倍的数字。
     还是想说明一点的是，无论multiAdd方法是否加了synchronized关键字，最后执行的结果肯定是正确的，也就是说，无论中间
     执行的过程是怎样的，你在打印的数字中，肯定可以找到1000这个数字，ai的最终值肯定是1000，所以说最后的结果肯定是对的
     （1000没有在最后一次打印，这是System.out.printl的问题，与结果正确与否无关）。结果本身是没有问题的，但是由于multiAdd
     方法时一个复合操作，也没有加锁，所以如果使用multiAdd方法每一次的返回结果作为结果的话，那么有可能就是有问题的，因为
     会出现非10的整数倍的数字的情况。但是加上锁以后，使用multiAdd方法的返回结果作为结果也是没有问题的，因为保证了方法整体的
     原子性。
     总结：atomic只能保证自身是原子性的，并不能保证整个方法是原子性的，所以如果想保证multiAdd方法返回结果是原子性的，
     那么还得给方法加锁。volatile关键字只具有可见性，没有原子性。要实现原子性建议使用atomic类的系列对象，支持原子性操作
     （注意atomic类只保证本身方法的原子性，并不保证多次操作的原子性）
     */
}
