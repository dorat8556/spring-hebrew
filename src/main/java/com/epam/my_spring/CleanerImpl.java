package com.epam.my_spring;

/**
 * @author Evgeny Borisov
 */
@TimerPerformance
public class CleanerImpl implements Cleaner {


    @InjectRandomInt(min = 3, max = 7)
    private int repeat;


    public void init() {
        System.out.println("repeat = " + repeat);
    }


    @Override

    public void clean() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("VVVVVVVVVVVVVVvvvvvvvvvvvv");
        }
    }

    @Override
    public void stam() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("sataaaaaaammm");
        }
    }
}
