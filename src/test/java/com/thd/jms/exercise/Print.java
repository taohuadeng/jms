package com.thd.jms.exercise;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Print {
    private static final Log LOG = LogFactory.getLog(Print.class);

    public static void main(String[] args) {
        Integer a = 256;
        Print p = new Print();
        p.add(a);
        System.out.println("main 中：" + a);
    }

    public void add(Integer a) {
        a = 456;
        System.out.println("add 中：" + a);
    }
}