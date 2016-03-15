package com.share.thread.callable;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableWithCountLath {
    public static final int COUNT = 100;
    //    static ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(10);
        //线程池维护线程的最少数量
        taskExecutor.setCorePoolSize(5);
        //线程池维护线程的最大数量
        taskExecutor.setMaxPoolSize(10);
        //线程池维护线程所允许的空闲时间
        taskExecutor.setKeepAliveSeconds(15);

        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);

        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutorPolicy.WaitPolicy());

        taskExecutor.initialize();

        CountDownLatch latch = new CountDownLatch(COUNT);
        List<Future<String>> futures = new ArrayList<Future<String>>(COUNT);
        for (int i = 1; i <= COUNT; i++) {
            Future<String> submit = taskExecutor.submit(new MyCallable(latch));
            futures.add(submit);
            System.out.println(taskExecutor.getCorePoolSize() + "   " + taskExecutor.getActiveCount());
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future<String> future : futures) {
            System.out.println("执行结果：" + future.get());
        }

        System.out.println(taskExecutor.getCorePoolSize() + "   " + taskExecutor.getActiveCount());
        //taskExecutor.shutdown();
    }
}

class MyCallable implements Callable<String> {
    private CountDownLatch latch;

    MyCallable(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        latch.countDown();
        return Thread.currentThread().getName();
    }

}
