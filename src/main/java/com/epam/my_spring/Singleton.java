package com.epam.my_spring;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Singleton {
    boolean lazy() default false;
}
