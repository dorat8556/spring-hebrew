package com.epam.my_spring;

import lombok.Setter;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Evgeny Borisov
 */
public class ObjectFactory {



    private static ObjectFactory instance = new ObjectFactory();
    private Config config;
    private Reflections scanner;
    private List<ObjectConfigurator> configurators =new ArrayList<>();


    public static ObjectFactory getInstance() {
        return instance;
    }


    @SneakyThrows
    public <T> T createObject(Class<T> type) {

        type = resolveImpl(type);


        T t = create(type);


        configure(t);
//todo here you should scan your class for methods, which starts with init and than you invoke them

        invokeAllInitMethods(t);

        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(conurator->conurator.configure(t));
    }

    private <T> T create(Class<T> type) throws InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        return type.getDeclaredConstructor().newInstance();
    }

    private <T> Class<T> resolveImpl(Class<T> type) {
        if (type.isInterface()) {
            Class<T> implClass = config.getImplClass(type);
            if (implClass == null) {

                Set<Class<? extends T>> classes = scanner.getSubTypesOf(type);
                if (classes.size() != 1) {
                    throw new IllegalStateException(type + " has 0 or more than one impl, please update your config");
                }
                implClass = (Class<T>) classes.iterator().next();
            }
            type = implClass;
        }
        return type;
    }

    @SneakyThrows
    private <T> void invokeAllInitMethods(T t){
        Method[] methods = t.getClass().getMethods();
//        Arrays.stream(methods).filter(method -> method.getName().contains("Init"))
//                .map(method -> method.invoke(t,method.getParameters()));
        for(Method m : methods){
            if(m.getName().contains("Init")){
                m.invoke(t);
            }
        }
    }

    public void setConfig(Config config){
        this.config = config;
        initScanner();
    }

    private void initScanner() {
        this.scanner = new Reflections(config.getPackagesToScan());
        initConfigurators();
    }

    @SneakyThrows
    private void initConfigurators() {
        Set<Class<? extends ObjectConfigurator>> classes = scanner.getSubTypesOf(ObjectConfigurator.class);
        for (Class<? extends ObjectConfigurator> aClass : classes) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }


}












