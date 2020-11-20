package com.epam.my_spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class PerformanceCheckerProxy implements ProxyConfigurator {

    @Override
    public Object replaceWithProxy(Object object) {
        Class<?> aClass = object.getClass();
        if (Arrays.stream(aClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(TimerPerformance.class))) {
            return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), aClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object val = null;
                    if (method.isAnnotationPresent(TimerPerformance.class)) {
                        long start = System.nanoTime();
                        val = method.invoke(object, args);
                        long end = System.nanoTime();
                        System.out.println("the time it took for : " + method.getName() + " is : " + (end - start) + "\n");
                    } else {
                        val = method.invoke(object, args);
                    }
                    return val;
                }
            });
        } else {
            return object;
        }
    }
}
