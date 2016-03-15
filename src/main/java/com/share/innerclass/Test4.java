package com.share.innerclass;

/**
 * 匿名内部
 */
public class Test4 {
    public static void main(String[] args) {
        Person p = new Person() {
            @Override
            public void eat() {
                System.out.println("Cat eating");
            }
        };

        p.eat();
    }
}

interface Person{
    void eat();
}
