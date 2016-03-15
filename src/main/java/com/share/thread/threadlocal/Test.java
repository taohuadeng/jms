package com.share.thread.threadlocal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 有返回值的线程
 */
@SuppressWarnings("unchecked")
public class Test {
    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        System.out.println("----程序开始运行----");
        Date date1 = new Date();

        int taskSize = 5;
        CountDownLatch latch = new CountDownLatch(taskSize);//两个工人的协作
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallable(i + " ", latch);
            // 执行任务并获取Future对象
            Future f = pool.submit(c);
            // System.out.println(">>>" + f.get().toString());
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();

        latch.await();
        // 获取所有并发任务的运行结果
        for (Future f : list) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            System.out.println(">>>" + f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }
}

class MyCallable implements Callable<Object> {
    private String taskNum;
    private CountDownLatch latch;

    MyCallable(String taskNum, CountDownLatch latch) {
        this.taskNum = taskNum;
        this.latch = latch;
    }

    public Object call() throws InterruptedException {
        System.out.println(">>>" + taskNum + "任务启动 " + Thread.currentThread().getName());
        Date dateTmp1 = new Date();
        Thread.sleep(1000);
        Date dateTmp2 = new Date();
        long time = dateTmp2.getTime() - dateTmp1.getTime();
        System.out.println(">>>" + taskNum + "任务终止 " + Thread.currentThread().getName());
        latch.countDown();
        return taskNum + Thread.currentThread().getName() + " 任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}