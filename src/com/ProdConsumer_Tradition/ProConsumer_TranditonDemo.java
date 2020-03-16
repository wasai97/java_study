package com.ProdConsumer_Tradition;

import java.util.Date;

/**
 * 生产者消费者模式
 */
class Data{
    public int a = 0;
    public synchronized void add() throws InterruptedException {
        while (a != 0){
            this.wait();
        }
        a++;
        System.out.println(a);
        notifyAll();

    }
    public synchronized void sub() throws InterruptedException {
        while (a == 0){
           this.wait();
        }
        a--;
        System.out.println(a);
        notifyAll();

    }
}
public class ProConsumer_TranditonDemo {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            try {
                for (int i = 0;i<10;i++){
                    data.add();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                for (int i = 0;i<10;i++){
                    data.sub();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
