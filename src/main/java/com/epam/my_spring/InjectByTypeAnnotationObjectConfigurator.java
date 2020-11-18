package com.epam.my_spring;


import lombok.SneakyThrows;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {


    @Override
    @SneakyThrows
    public void configure(Object t) {
        Class<?> aClass = t.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for(Field field : fields){
            InjectByType annotation = field.getAnnotation(InjectByType.class);
            if(annotation != null){
                Object c = null;
                Class<?> type = field.getType();
                c = ObjectFactory.getInstance().createObject(type);
                field.setAccessible(true);
                field.set(t,c);
            }
        }

    }
}
