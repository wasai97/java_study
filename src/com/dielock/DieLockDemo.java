package com.dielock;

class Thread1 implements Runnable{
    @Override
    public void run() {
        synchronized (DieLockDemo.lock1){
            System.out.println(Thread.currentThread().getName()+"获取lock1，等待lock2");
            try {
                Thread.sleep(2000);
                synchronized (DieLockDemo.lock2){
                    System.out.println(Thread.currentThread().getName()+"lock2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        synchronized (DieLockDemo.lock2){
            System.out.println(Thread.currentThread().getName()+"获取lock2，等待lock1");
            try {
                Thread.sleep(2000);
                synchronized (DieLockDemo.lock1){
                    System.out.println(Thread.currentThread().getName()+"lock1");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DieLockDemo {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    public static void main(String[] args) {
        new Thread(new Thread1(),"A").start();
        new Thread(new Thread2(),"B").start();
    }
}
