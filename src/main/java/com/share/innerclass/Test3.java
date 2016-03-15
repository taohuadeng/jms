package com.share.innerclass;

/**
 * 静态内部类
 */
public class Test3 {
    public static void main(String[] args) {
        Outer3 outer3 = new Outer3();
        outer3.test();

        Outer3.Inner3 inner3 = new Outer3.Inner3();
        Outer3.Inner3.sayHello();
    }
}

class Outer3 {
    static class Inner3 {
        Inner3() {
            System.out.println("Inner3 init");
        }

        Inner3(int i) {
            this.i = i;
        }

        private int i = 5;

        public static void sayHello(){
            System.out.println("Hello Inner3");
        }
    }

    public void test() {
        Inner3 inner3 = new Inner3();
        System.out.println(inner3.i);

        Outer3.Inner3 inner31 = new Outer3.Inner3();
        System.out.println("外部类访问内部类的private属性 " + inner31.i);
    }
}
