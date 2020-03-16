package com.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Teacher teacher = new MathTeacher();
        ProxyTeacher proxyTeacher = new ProxyTeacher(teacher);
        Teacher poxy = (Teacher) proxyTeacher.getPoxy();
        poxy.teach();
    }
}
