package com.epam.my_spring;



/**
 * @author Evgeny Borisov
 */
public class IRobot {


    private Speaker speaker = ObjectFactory.getInstance().createObject(Speaker.class);
    private Cleaner cleaner = ObjectFactory.getInstance().createObject(Cleaner.class);




    public void cleanRoom() {
        speaker.say("I started");
        cleaner.clean();
        speaker.say("I finshed");
    }
}
