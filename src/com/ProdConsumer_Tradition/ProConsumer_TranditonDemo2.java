package com.ProdConsumer_Tradition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Data2{
    private int a = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void add(){
        lock.lock();
        try {
            while (a != 0){
                condition.await();
            }
            a++;
            System.out.println(a);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sub(){
        lock.lock();
        try {
            while (a != 1){
                condition.await();
            }
            a--;
            System.out.println(a);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ProConsumer_TranditonDemo2 {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
                for (int i = 0;i<10;i++){
                    data.add();
                }
        },"A").start();

        new Thread(()->{
                for (int i = 0;i<10;i++){
                    data.sub();
                }
        },"B").start();
    }
}
