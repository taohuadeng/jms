package com.share.synchronize;

public class MyThread implements Runnable {
    private int i = 0;

    public static void main(String args[]) {
        MyThread mt = new MyThread();
        Thread t1 = new Thread(mt, "t1");
        Thread t2 = new Thread(mt, "t2");
        Thread t3 = new Thread(mt, "t3");
        Thread t4 = new Thread(mt, "t4");
        Thread t5 = new Thread(mt, "t5");
        Thread t6 = new Thread(mt, "t6");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    public void run() {
//        synchronized (this) {
            System.out.println(++i);
            System.out.println(Thread.currentThread().getName());
//        }

        System.out.println("new "+Thread.currentThread().getName());
    }
}
