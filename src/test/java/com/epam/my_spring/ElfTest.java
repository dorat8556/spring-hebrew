package com.epam.my_spring;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Evgeny Borisov
 */
public class ElfTest {

    @Test
    public void getPower() {

        Elf elf = ObjectFactory.getInstance().createObject(Elf.class);
        Assert.assertTrue(elf.getPower()>=10);
        Assert.assertTrue(elf.getPower()<=20);
    }

    @Test
    public void saySomthing(){
        Elf elf = ObjectFactory.getInstance().createObject(Elf.class);
        Assert.assertTrue(!elf.getSay().equals(""));
    }
}