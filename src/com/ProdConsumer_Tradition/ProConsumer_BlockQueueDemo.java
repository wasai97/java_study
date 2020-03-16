package com.ProdConsumer_Tradition;

import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyBlock{
    private volatile boolean flag = true;//true代表生产，false代表不生产
    private AtomicInteger atomicInteger = new AtomicInteger();//代表蛋糕
    BlockingQueue<String> blockingDeque ;//阻塞队列存储
    public MyBlock(BlockingQueue blockingDeque){
        this.blockingDeque = blockingDeque;
    }
    //生产方法
    public void myPro() throws InterruptedException {
        String data = null;
        boolean res;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            res = blockingDeque.offer(data,2, TimeUnit.SECONDS);
            if (res){
                System.out.println(Thread.currentThread().getName()+"\t"+"生产成功："+data);
                Thread.sleep(2000);
            }else {
                System.out.println(Thread.currentThread().getName()+"队列已满，生产失败。");
            }
        }
        System.out.println("大老板发话，停止生产");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (flag){
            //一直在这循环取
            result = blockingDeque.poll(2,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName()+"消费成功"+result);
            Thread.sleep(2000);
            if (!flag){
                System.out.println("结束消费。");
            }
        }
    }
    //结束生产
    public void stop(){
        flag = false;
    }
}
public class ProConsumer_BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        MyBlock myBlock = new MyBlock(new ArrayBlockingQueue<String>(1));
        new Thread(()->{
            try {
                myBlock.myPro();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"pro").start();

        new Thread(()->{
            try {
                myBlock.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer").start();

        Thread.sleep(5000);//让他们生产5秒
        myBlock.stop();
        System.out.println("大老板喊停了");
    }
}
