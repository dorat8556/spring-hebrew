package com.epam.my_spring;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Evgeny Borisov
 */

public class ConsoleSpeaker implements Speaker {
    @Override
    public void say(String message) {
        System.out.println(message);
    }
}
