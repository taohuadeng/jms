package com.share.clone;

public class Test {
    public static void main(String[] args) {
       SubCloneDemo demo = new SubCloneDemo();
        demo.name = "123";
        try {
            SubCloneDemo clone = (SubCloneDemo) demo.clone();
            System.out.println(clone.name);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
