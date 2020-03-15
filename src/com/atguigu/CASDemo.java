package com.atguigu;

import java.util.concurrent.atomic.AtomicReference;

class User{
    private int age;
    private String name;
    public User(int age,String name){
        this.age =age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
public class CASDemo {
    public static void main(String[] args) {
        User zs = new User(20,"张三");
        User ls = new User(20,"李四");
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);
        System.out.println(atomicReference.compareAndSet(zs,ls));
        System.out.println(atomicReference.get().toString());
    }
}
