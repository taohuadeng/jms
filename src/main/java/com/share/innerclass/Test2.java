package com.share.innerclass;

/**
 * 局部内部类
 */
public class Test2 {
    public static void main(String[] args) {
        Outer2 outer2 = new Outer2();
        outer2.test();
//
//        Outer2_2.test();
    }
}

class Outer2 {
    private int i = 9;
    private int a = 9;

    public void test() {
        final String name ="TaoHuaDeng";

        class Inner2 {
            Inner2() {
                System.out.println("Inner2 init");
                System.out.println(name);
            }

            private int i = 8;

            public void test(){
                Outer2 outer2 = new Outer2();
                System.out.println(outer2.i);
                System.out.println(a);
            }
        }

        Inner2 inner2 = new Inner2();
        inner2.test();
        System.out.println(inner2.i);
    }
}

class Outer2_2 {
    private int i = 9;
    private int a = 9;

    public static void test() {
        class Inner2_2 {
            private int i = 8;

            public void test(){
                Outer2_2 outer2 = new Outer2_2();
                System.out.println(outer2.i);
            }
        }

        Inner2_2 inner2 = new Inner2_2();
        inner2.test();
        System.out.println(inner2.i);
    }
}
