package com.epam.my_spring;

import lombok.SneakyThrows;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class PerformanceCheckerProxy implements ProxyConfigurator {

    @Override
    public Object replaceWithProxy(Object object) {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(TimerPerformance.class) ||
                Arrays.stream(aClass.getMethods()).anyMatch(method -> method.isAnnotationPresent(TimerPerformance.class))) {
            if (object.getClass().getInterfaces().length > 0) {
                return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), aClass.getInterfaces(),
                        (proxy, method, args) -> getInvocationHandler(object, proxy, method, args));
            } else {
                return Enhancer.create(aClass, (net.sf.cglib.proxy.InvocationHandler)
                        (o, method, objects) -> getInvocationHandler(object, o, method, objects));
            }
        } else {
            return object;
        }
    }

    @SneakyThrows
    private Object getInvocationHandler(Object t, Object proxy, Method method, Object[] args) {
        if (method.isAnnotationPresent(TimerPerformance.class) || t.getClass().isAnnotationPresent(TimerPerformance.class)) {
            long start = System.nanoTime();
            Object retVal = method.invoke(t, args);
            long end = System.nanoTime();
            System.out.println("the time it took for : " + method.getName() + " is : " + (end - start) + "\n");
            return retVal;
        } else {
            return method.invoke(t, args);
        }
    }
}
