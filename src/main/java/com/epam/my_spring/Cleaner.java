package com.epam.my_spring;

/**
 * @author Evgeny Borisov
 */
public interface Cleaner {
    @TimerPerformance
    void clean();
    void stam();
}
