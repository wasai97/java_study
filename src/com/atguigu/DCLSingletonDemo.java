package com.atguigu;

public class DCLSingletonDemo {
    //使用volatile禁止指令重排
    private static volatile DCLSingletonDemo instance = null;
    private DCLSingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"dclsingleton constructor");
    }

    //DCL(double check lock)双端检锁机制
    public static DCLSingletonDemo getInstance(){
        if (instance == null){
            synchronized (DCLSingletonDemo.class){
                if (instance == null){
                    instance = new DCLSingletonDemo();
                    return instance;
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            new Thread(()->{
                DCLSingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
