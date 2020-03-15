package com.atguigu;

import java.util.concurrent.atomic.AtomicInteger;

class Data{
    volatile int num = 0;
    //可以解决volatile的原子性
    AtomicInteger atomicInteger = new AtomicInteger();
    public  void setNum(){
        this.num = 60;
    }

    public  void add(){
        num++;
    }

    public void addAtomicInteger(){
        atomicInteger.getAndIncrement();
        atomicInteger.compareAndSet(5,10);
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        notAtomic();
    }

    //测试volatile不保证原子性
    //使用AtomicInteger可以保证原子性
    private static void notAtomic() {
        Data data = new Data();
        for (int i = 1;i<=20;i++){
            new Thread(()->{
                for (int j=0;j<2000;j++){
                    data.add();
                    data.addAtomicInteger();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            //线程礼让
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"finish number:"+data.num);
        System.out.println(Thread.currentThread().getName()+"finish atomicInteger:"+data.atomicInteger);
    }

    //验证volatile的可见性，当一个线程修改数据后，其他线程可以立即知道
    private static void seeOkVolatile() {
        Data data = new Data();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"come in");
            try {
                Thread.sleep(3000);
                data.setNum();
                System.out.println(Thread.currentThread().getName()+"out");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        while (data.num == 0){}
        System.out.println(Thread.currentThread().getName()+"finish");
    }
}
