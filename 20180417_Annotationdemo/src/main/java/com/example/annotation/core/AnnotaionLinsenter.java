package com.example.annotation.core;

import java.lang.reflect.Method;

public class AnnotaionLinsenter {

    public static void getAnnotation(Class clazz){
        // 通过反射获取类中方法中的注解，然后通过注解获取注解中的值
        Method[] methods=clazz.getDeclaredMethods();
        for(Method method:methods){
            if(method.isAnnotationPresent(LogAnnotaion.class)){
                LogAnnotaion annotaion=method.getAnnotation(LogAnnotaion.class);
                String annotationInfo=annotaion.level();
                System.out.printf(clazz.getName()+"类中方法的注解level属性的值为："+annotationInfo);
            }
        }
    }

}
