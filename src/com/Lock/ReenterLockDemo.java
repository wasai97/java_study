package com.Lock;

/**
 * 可重入锁：线程可以进入任何一个他已经拥有的锁 所同步着的代码块
 * synchronized和ReentrantLock都是可重入锁
 */
class Phone extends Thread{
    public synchronized void sendMessage(){
        System.out.println(Thread.currentThread().getName()+"sendMessage");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"sendEmail");
    }
}
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.sendMessage();
        },"t1").start();
        new Thread(()->{
            phone.sendMessage();
        },"t2").start();
    }
}
