package com.share.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Player implements Runnable {
    private int threadId;
    private CountDownLatch beginLatch;
    private CountDownLatch endLatch;

    public Player(int threadId, CountDownLatch beginLatch, CountDownLatch endLatch) {
        super();
        this.threadId = threadId;
        this.beginLatch = beginLatch;
        this.endLatch = endLatch;
    }

    @Override
    public void run() {
        try {
            beginLatch.await();        //等待begin的状态为0
            Thread.sleep((long) (Math.random() * 100));    //随机分配时间，即运动员完成时间
            System.out.println("Player_" + threadId + " arrived.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            endLatch.countDown();    //使end状态减1，最终减至0
        }
    }
}
