package com.example.aopdemo;

import com.example.aopdemo.pojo.IPerson;
import com.example.aopdemo.pojo.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainTest {

    public static void main1(String[] args) {
        // aop不能直接应用与类上面，它必须依赖一个接口而实现，以下是基于一个类和一个接口的demo
        final IPerson person = (IPerson)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{IPerson.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 将类进行动态代理之后该类执行的方法就会被拦截
                String str="hello";
                return method.invoke(new Person(),args);
            }
        });
       String str= person.sayHello("zh");
       System.out.printf(str);
    }


    public static void main2(String[] args) {
        // 以下是一个基于接口的aop实现
        final IPerson person = (IPerson)Proxy.newProxyInstance(IPerson.class.getClassLoader(), new Class[]{IPerson.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String str="hello";
                System.out.printf(str);
                return null;
            }
        });
        person.sayHello("zh");
    }
}
