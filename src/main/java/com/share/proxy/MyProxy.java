package com.share.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {
    private Object subject;

    public MyProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass());
        System.out.println("123".getClass());
        if (args != null) {
            for (Object arg : args) {
                System.out.println(arg);
            }
        }

        System.out.println("before rent house");

        System.out.println("Method:" + method);

        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");

        return null;
    }
}
