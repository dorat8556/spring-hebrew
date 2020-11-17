package com.epam.my_spring;


import lombok.SneakyThrows;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    private static Set<Object> objects = new HashSet<>();

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
                for(Object o : objects){
                    if(o.getClass() == type){
                        c=o;
                        break;
                    }
                }
                if( c == null){
                    c = ObjectFactory.getInstance().createObject(type);
                    objects.add(c);
                }
                field.setAccessible(true);
                field.set(t,c);
            }
        }

    }
}
