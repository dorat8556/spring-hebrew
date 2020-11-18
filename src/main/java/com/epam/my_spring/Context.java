package com.epam.my_spring;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Context {

    Map<Class,Object> objectContainer = new HashMap<>();

    Context(String packageToScan,Map<Class, Class> ifc2ImplClass){
        ObjectFactory.getInstance().setConfig(new CustomConfig(ifc2ImplClass,packageToScan));
        Reflections reflections = new Reflections(packageToScan);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Singleton.class);
        for(Class<?> annotatedClass : annotatedClasses){
            Singleton annotation = annotatedClass.getAnnotation(Singleton.class);
            if(!annotation.lazy()){
                getObject(annotatedClass);
            }
        }
    }

    Object getObject(Class type){
        if(objectContainer.containsKey(type)){
            return objectContainer.get(type);
        }else{
            Object obj = ObjectFactory.getInstance().createObject(type);
            if(type.getAnnotation(Singleton.class) != null){
                objectContainer.put(type,obj);
            }
            return obj;
        }
    }
}
