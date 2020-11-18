package com.epam.my_spring;

import java.util.Map;

/**
 * @author Evgeny Borisov
 */
public class Main {
    public static void main(String[] args) {
        Map<Class, Class> ifc2ImplClass = Map.of(
                Speaker.class, ConsoleSpeaker.class
        );
        Context context = new Context("com.epam",ifc2ImplClass);
        IRobot iRobot = (IRobot) context.getObject(IRobot.class);

//        IRobot iRobot = ObjectFactory.getInstance().createObject(IRobot.class);
        iRobot.cleanRoom();

        //todo add support for @InjectRandomString(type=StringTypes.CHUCK_NORRIS_QUOTE,
        // type = StringTypes.GAME_OF_THRONES)
        // todo write support for running automticly all methods which starts with init
    }
}
