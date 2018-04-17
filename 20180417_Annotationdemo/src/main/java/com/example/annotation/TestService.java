package com.example.annotation;

import com.example.annotation.core.LogAnnotaion;

public class TestService {

    @LogAnnotaion(level = "debug")
    public String sayHello(String name){
        return "hi "+name;
    }
}
