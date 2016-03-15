package com.share.thread.callable;

import java.util.ArrayList;
import java.util.concurrent.*;

class TaskWithResult implements Callable<String> {
    private int threadId;

    public TaskWithResult(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public String call() throws Exception {
        //Thread.sleep(1000);
        return "result of TaskWithResult " + threadId;
    }
}

public class CallableTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();    //Future 相当于是用来存放Executor执行的结果的一种容器
        for (int i = 0; i < 10; i++) {
            results.add(executor.submit(new TaskWithResult(i)));
        }

        for (Future<String> fs : results) {
            if (fs.isDone()) {
                System.out.println(fs.get());
            } else {
                System.out.println("Future result is not yet complete");
            }
        }

        executor.shutdown();
    }
}

