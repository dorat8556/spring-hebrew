package com.epam.my_spring;

import lombok.SneakyThrows;
import net.sf.cglib.proxy.Enhancer;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PerformanceCheckerProxy implements ProxyConfigurator {

    @Override
    public Object replaceWithProxy(Object object, Class implClass) {
        if (implClass.isAnnotationPresent(TimerPerformance.class) ||
                ReflectionUtils.getAllMethods(implClass).stream().anyMatch(method -> method.isAnnotationPresent(TimerPerformance.class))) {
            if (implClass.getInterfaces().length > 0) {
                return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
                        (proxy, method, args) -> getInvocationHandler(object, implClass, method, args));
            } else {
                return Enhancer.create(implClass, (net.sf.cglib.proxy.InvocationHandler)
                        (o, method, objects) -> getInvocationHandler(object, implClass, method, objects));
            }
        } else {
            return object;
        }
    }

    @SneakyThrows
    private Object getInvocationHandler(Object t, Class implClass, Method method, Object[] args) {
        if (implClass.isAnnotationPresent(TimerPerformance.class)
                || implClass.getMethod(method.getName(),method.getParameterTypes()).isAnnotationPresent(TimerPerformance.class)) {
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
