package com.example.aopannotaion.core;

import java.lang.annotation.*;


@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {
    String sql();
}
