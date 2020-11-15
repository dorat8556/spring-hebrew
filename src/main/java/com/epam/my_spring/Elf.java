package com.epam.my_spring;

import lombok.Data;
import lombok.ToString;

import java.util.Random;

/**
 * @author Evgeny Borisov
 */
@Data
public class Elf {
    @InjectRandomInt(min = 10,max = 20)
    private int power;

    private int age=120;

    @InjectRandomInt(min = 3,max = 7)
    private int speed;


}
