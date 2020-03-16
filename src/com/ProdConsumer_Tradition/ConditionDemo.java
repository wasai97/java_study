package com.ProdConsumer_Tradition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Print{
    private Lock lock = new ReentrantLock();
    private int flage = 1;//1代表A，2代表B，3代表C
    private Condition A = lock.newCondition();//唤醒A线程
    private Condition B = lock.newCondition();//唤醒B线程
    private Condition C = lock.newCondition();//唤醒C线程
    public void printA(int num){
        lock.lock();
        try {
            while (flage != 1){
                A.await();
            }
            for (int i = 0; i < num; i++) {
                System.out.println(Thread.currentThread().getName()+"print");
            }
            flage = 2;
            B.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int num){
        lock.lock();
        try {
            while (flage != 2){
                B.await();
            }
            for (int i = 0; i < num; i++) {
                System.out.println(Thread.currentThread().getName()+"print");
            }
            flage = 3;
            C.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int num){
        lock.lock();
        try {
            while (flage != 3){
                C.await();
            }
            for (int i = 0; i < num; i++) {
                System.out.println(Thread.currentThread().getName()+"print");
            }
            flage = 1;
            A.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class ConditionDemo {
    public static void main(String[] args) {
        Print print = new Print();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                print.printA(5);
            },"A").start();

            new Thread(()->{
                print.printB(10);
            },"B").start();

            new Thread(()->{
                print.printC(15);
            },"C").start();
        }
    }
}
