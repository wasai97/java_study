package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTeacher {
    private Teacher teacher;
    public ProxyTeacher(Teacher teacher){
     this.teacher = teacher;
    }

    /**
     * 核心方法
     * @return
     */
    public Object getPoxy(){
        return Proxy.newProxyInstance(ProxyTeacher.class.getClassLoader(), teacher.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy come in");
                Object invoke = method.invoke(teacher, args);
                return invoke;
            }
        });
    }
}
