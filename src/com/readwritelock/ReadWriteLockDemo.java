package com.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源来，就不应该再有其它线程可以对资源进行读或写
 * 小总结：
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 */
class MyCache {
    private Map<String, Object> map = new HashMap<>();
    //读写所
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public void put(String key, Object value) {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入：" + key);
            Thread.sleep(1000);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        reentrantReadWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"正在读");
            Object result = map.get(key);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"读取完成"+result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int i1 =i;
            new Thread(()->{
                myCache.put(i1+"",i1);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int i1 =i;
            new Thread(()->{
                myCache.get(i1+"");
            },String.valueOf(i)).start();
        }
    }
}
