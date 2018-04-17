package com.example.aopannotaion;

import com.example.aopannotaion.core.Select;
import com.example.aopannotaion.dao.PersonDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainTest {

    public static void main(String[] args) {
        PersonDao personDao=(PersonDao)Proxy.newProxyInstance(PersonDao.class.getClassLoader(), new Class[]{PersonDao.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String sql=null;
                // 通过反射得到该类的方法，再通过方法拿到方法上的注解
                Method[] methods=PersonDao.class.getDeclaredMethods();
                for(Method m:methods){
                    if(m.isAnnotationPresent(Select.class)){
                        Select selectAnnotation=m.getAnnotation(Select.class);
                        sql= selectAnnotation.sql();
                    }
                }
                String arg=args[0].toString();
                String param=sql.replaceAll("[?]",arg);
                System.out.println("mysql查询语句为："+param);
                // to do 执行mysql查询
                String result ="张三";
                return result;
            }
        });

        String result=personDao.findNameById(2);
        System.out.println("mysql查询结果为："+result);
    }
}
