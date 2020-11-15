package com.epam;

import com.epam.lombok_examples.Person;

/**
 * @author Evgeny Borisov
 */
public class HelloService {
    public static void main(String[] args) {

        Person person = Person.builder().age(12).דג("carpion").name("Jeka").beer("Tuborg",1).beer("Leff",2).build();

        System.out.println("person = " + person);

        Person person1 = person.withAge(20);
        System.out.println("person1 = " + person1);

        String s = "hava";
        String s2 = new String("hava");
        System.out.println(s==s2);
    }

}
