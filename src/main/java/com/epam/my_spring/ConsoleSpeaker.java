package com.epam.my_spring;

/**
 * @author Evgeny Borisov
 */

public class ConsoleSpeaker implements Speaker {
    @Override
    @TimerPerformance
    public void say(String message) {
        System.out.println(message);
    }
}
