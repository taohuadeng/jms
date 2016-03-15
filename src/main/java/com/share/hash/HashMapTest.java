package com.share.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    private volatile String name;
    public static void main(String[] args) {
        HashMap<String,String> hashMap = new HashMap<String, String>(2);
        hashMap.put(null,null);
        hashMap.put(null,"null");
        hashMap.put("test","test");
        String s = hashMap.get(null);
        System.out.println(s);
        boolean b = hashMap.containsKey(null);
        System.out.println(b);
        System.out.println(s != null);
        List<String> list = new ArrayList<String>(5);
        for (int i = 0; i < 5; i++) {
            list.add(null);
        }

        list.contains(null);
    }
}
