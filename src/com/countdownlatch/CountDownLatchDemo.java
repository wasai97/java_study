package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"上完自习，离开教室。");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();//必须要减到0才能通过
        System.out.println(Thread.currentThread().getName()+"所有人走完，班长锁门。");
    }
}
