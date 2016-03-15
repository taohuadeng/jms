package com.share.extends_;

import junit.framework.TestCase;

public class MySubTest extends TestCase {
    static {
        a = 5;
    }

    static int a = 6;
    public static void main(String[] args) {
        System.out.println(a);
    }
}
