package com.seamphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 模拟6部车抢三个停车位
 */
public class SeamPhoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个停车位
        //模拟6辆车
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//代表抢到资源
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"抢到停车位");
                try {
                    TimeUnit.SECONDS.sleep(3);//模拟停车三秒
                    System.out.println(Thread.currentThread().getName()+"停车三秒后离开车位。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放资源
                }
            },String.valueOf(i)).start();
        }
    }
}
