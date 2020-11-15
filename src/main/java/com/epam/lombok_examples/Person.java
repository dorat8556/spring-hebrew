package com.epam.lombok_examples;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author Evgeny Borisov
 */


@Builder
@ToString
@Value
@With
@Data
public class Person {

    private int id;


    private String name;

    private int age;

    private String lastName;


    @Singular
    private Map<String,Integer> beers;


    @Singular("דג")
    private List<String> fish;



}
