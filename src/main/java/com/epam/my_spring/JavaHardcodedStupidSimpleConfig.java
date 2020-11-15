package com.epam.my_spring;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public class JavaHardcodedStupidSimpleConfig implements Config {


    private Map<Class,Class> ifc2ImplClass = Map.of(
            Speaker.class,ConsoleSpeaker.class


    );



    @Override
    public <T> Class<T> getImplClass(Class<T> type) {
        return ifc2ImplClass.get(type);
    }

    @Override
    public String getPackagesToScan() {
        return "com.epam";
    }
}
