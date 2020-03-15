package com.spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁：是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式是尝试取锁，这样的好处是减少线程上下文切换的消耗，缺点是循环会消耗CPU
 * 自旋锁的好处：循环比较获取直到成功为止，没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，
 */

public class SpinLockDemo {
    //原子引用线程
    private static AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread+"come in");
        while (!atomicReference.compareAndSet(null,thread)){
            //CAS思想
        }
    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread+"out ");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            try {
                spinLockDemo.myLock();
                Thread.sleep(5);
                spinLockDemo.unLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.unLock();
        },"B").start();
    }
}
