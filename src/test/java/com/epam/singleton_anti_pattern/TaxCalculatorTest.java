package com.epam.singleton_anti_pattern;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * @author Evgeny Borisov
 */
public class TaxCalculatorTest {

    @Test
    public void withMaam() {

        MaamResolver mock = Mockito.mock(MaamResolver.class);
        Mockito.when(mock.getMaam()).thenReturn(0.17);

        TaxCalculator taxCalculator = new TaxCalculator(mock);
        double withMaam = taxCalculator.withMaam(100);
        Assert.assertEquals(117,withMaam,0.000001);

    }


}