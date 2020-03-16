package com.threadpool;

import java.util.concurrent.*;

public class MyTreadPool {
    public static void main(String[] args) {
        ExecutorService threadpool = new ThreadPoolExecutor(2,
                5,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 0; i < 9; i++) {
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }finally {
            threadpool.shutdown();
        }
    }
}
