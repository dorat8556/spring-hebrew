package com.epam.my_spring;



/**
 * @author Evgeny Borisov
 */
public class IRobot {


    //TODO add InjectByType ( like autowired)
//    private Speaker speaker = ObjectFactory.getInstance().createObject(Speaker.class);
//    private Cleaner cleaner = ObjectFactory.getInstance().createObject(Cleaner.class);
    @InjectByType
    private Speaker speaker;
    @InjectByType
    private Cleaner cleaner;




    public void cleanRoom() {
        speaker.say("I started");
        cleaner.clean();
        speaker.say("I finshed");
    }
}
