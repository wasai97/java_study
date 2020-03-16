package com.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 利用枚举类来控制国家名称
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"被灭");
                countDownLatch.countDown();
            },CountryEnum.getCountryEnum(i).getName()).start();
        }
        countDownLatch.await();//必须要减到0才能通过
        System.out.println(Thread.currentThread().getName()+"秦国一统天下。");
    }
}
