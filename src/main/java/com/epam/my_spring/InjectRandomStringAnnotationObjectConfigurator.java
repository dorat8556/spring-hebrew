package com.epam.my_spring;

import com.github.javafaker.ChuckNorris;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class InjectRandomStringAnnotationObjectConfigurator implements ObjectConfigurator {
    private Faker faker = new Faker();

    @Override
    @SneakyThrows
    public void configure(Object t) {

        Class<?> type = t.getClass();
        Map<StringTypes,String> mappy = new HashMap<>();
        Field[] declaredFields = type.getDeclaredFields();
        for (Field field : declaredFields) {
            InjectRandomString annotation = field.getAnnotation(InjectRandomString.class);
            String say;
            if(annotation != null){
                if(annotation.type() == StringTypes.CHUCK_NORRIS_QUOTE){
                     say = faker.chuckNorris().fact();
                }else{
                    say = faker.gameOfThrones().character();
                }
                field.setAccessible(true);
                field.set(t,say);
            }
        }
    }
}
