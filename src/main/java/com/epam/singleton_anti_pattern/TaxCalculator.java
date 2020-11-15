package com.epam.singleton_anti_pattern;

import lombok.AllArgsConstructor;

/**
 * @author Evgeny Borisov
 */
@AllArgsConstructor
public class TaxCalculator {


    private MaamResolver maamResolver;

    public double withMaam(double price) {

        double maam = maamResolver.getMaam();
        return price * maam + price;
    }



}
