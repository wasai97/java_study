package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 获取线程的方法：继承Thread类，实现Runnable接口，实现Callable接口，线程池
 * FutureTask实现了Runnable接口，FutureTask构造方法要求穿入一个Callable接口
 */
class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in callable");
        return 1024;
    }
}

/**
 * callable 接口有返回值
 * 如果只有一个futuretask，多个线程只会进入一个
 * 如果有多个futuretask，才会进入多次
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask, "AA");
        thread.start();
        Integer integer = futureTask.get();//这个方法获取返回值
        boolean done = futureTask.isDone();//判断任务是否完成
        System.out.println(integer);
    }
}
