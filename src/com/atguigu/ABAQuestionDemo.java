package com.atguigu;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ABA问题时，两个线程操作一个公共变量A，一线程操作需要10秒，二线程操作需要2秒，
 * 有可能出现的问题是二线程将变量修改为B，接着又将变量修改为A，
 * 当一线程执行完的时候发现变量还是A，会执行成功，但是其实中间二线程修改了两次
 * 这就是ABA问题
 * 解决方法：AtomicStampedReference
 */
public class ABAQuestionDemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("============ABA问题产生===========");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
        new Thread(()->{
            try {
                Thread.sleep(2000);
                atomicReference.compareAndSet(100,2019);
                System.out.println(Thread.currentThread().getName()+"当前值为："+atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        Thread.sleep(3000);
        System.out.println("============ABA问题解决===========");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"当前版本："+stamp);
            try {
                Thread.sleep(1000);
                atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
                atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"最终版本："+atomicStampedReference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"当前版本："+stamp);
            try {
                Thread.sleep(3000);
                boolean b = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName()+"执行结果："+b);
                System.out.println(Thread.currentThread().getName()+"最终结果："+atomicStampedReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t4").start();
    }
}
