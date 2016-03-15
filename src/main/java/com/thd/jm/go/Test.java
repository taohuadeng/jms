package com.thd.jm.go;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("123");
//        list.remove("123");

        List<String> testList = new ArrayList<String>(20);
        for (int i = 0; i < 20; i++) {
            String s = i + "Hello!";
            testList.add(s);
        }

        List<String> subList1 = testList.subList(0, 10);
        List<String> subList2 = testList.subList(11, 20);
        subList1.isEmpty();
        subList2.remove(0);
        //subList2.add("app");
    }
}
