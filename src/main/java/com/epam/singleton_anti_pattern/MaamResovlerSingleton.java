package com.epam.singleton_anti_pattern;


import lombok.Getter;
import lombok.SneakyThrows;

/**
 * @author Evgeny Borisov
 */
public class MaamResovlerSingleton {


    private static MaamResovlerSingleton instance = new MaamResovlerSingleton();
    @Getter
    private double maam;


    @SneakyThrows
    private MaamResovlerSingleton() {

        refreshMaam();


    }





    private void refreshMaam() throws InterruptedException {
        Thread.sleep(10000);
        maam = 0.18;
    }

    public static MaamResovlerSingleton getInstance() {
        return instance;
    }


}
