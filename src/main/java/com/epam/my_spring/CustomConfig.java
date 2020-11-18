package com.epam.my_spring;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public class CustomConfig implements Config {


    private Map<Class,Class> ifc2ImplClass;
    private String packageToScan;

    public CustomConfig(Map<Class, Class> ifc2ImplClass, String packageToScan) {
        this.ifc2ImplClass = ifc2ImplClass;
        this.packageToScan = packageToScan;
    }

    @Override
    public <T> Class<T> getImplClass(Class<T> type) {
        return ifc2ImplClass.get(type);
    }

    @Override
    public String getPackagesToScan() {
        return "com.epam";
    }
}
