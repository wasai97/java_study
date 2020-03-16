package com.proxy;

public class MathTeacher implements Teacher {
    @Override
    public void teach() {
        System.out.println("数学老师教数学");
    }
}
