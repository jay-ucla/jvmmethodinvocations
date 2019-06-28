package com.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("Dynamic Proxy hijacked : "+method.getName());

        return 42;
    }
}
