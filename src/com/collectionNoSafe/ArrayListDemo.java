package com.collectionNoSafe;

import java.util.*;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ArrayListDemo {
    public static void main(String[] args) {
        Set set = new CopyOnWriteArraySet();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(set);
            }).start();
        }

    }

    private static void listNoSafe() {
        //List list = new CopyOnWriteArrayList();
        List list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(list);
            }).start();
        }
    }
}
