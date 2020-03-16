package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService threadpool = Executors.newFixedThreadPool(5);//创造一个有五个线程的线程池
       try {
           for (int i = 0; i < 15; i++) {
               threadpool.execute(()->{
                   System.out.println(Thread.currentThread().getName()+"办理业务");
               });
           }
       }finally {
           threadpool.shutdown();
       }
    }
}
