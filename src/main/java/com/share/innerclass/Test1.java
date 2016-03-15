package com.share.innerclass;

/**
 * 非静态内部类
 */
public class Test1 {
    public static void main(String[] args) {
        Outer outer = new Outer();
//        outer.test();

//        Outer.Inner oi = new Outer().new Inner(20);
//        oi.i
    }
}

class Outer{
    private String outerName = "outerName";

    class Inner{
        Inner() {
            System.out.println("Inner init");
        }

        private int i = 5;

        Inner(int i) {
            this.i = i;
        }
    }

    public void test(){
        Inner inner = new Inner(2);
        System.out.println(inner.i);

        Outer.Inner oi = new Outer().new Inner(20);
        System.out.println(oi.i);
    }
}
