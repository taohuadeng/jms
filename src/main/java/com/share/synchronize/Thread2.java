package com.share.synchronize;

/**
 * 然而，当一个线程访问object的一个synchronized(this)同步代码
 * 块时，另一个线程仍然可以访问该object中的非synchronized(this)
 * 同步代码块。
 */
public class Thread2 {

    public void t1() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                String name = Thread.currentThread().getName();
                if("T1".equals(name) && i == 2){
                    try {
                        this.wait(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + " synchronized loop "
                        + i);

                if("T2".equals(name) && i == 3){
                   this.notify();
                }

//                try {
//                    this.wait();
//                    //Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }


        }
    }

    public void t2() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " synchronized loop "
                    + i);
        }
    }

    public static void main(String[] args) {
        final  Thread2 t2 = new Thread2();
        Thread ta = new Thread(new Runnable() {
            @Override
            public void run() {
                t2.t1();
            }
        },"T1");
        Thread tb = new Thread(new Runnable() {
            @Override
            public void run() {
                t2.t1();
            }
        },"T2");
        ta.start();
        tb.start();
    }
}