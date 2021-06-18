package studio.rrprojects.runnerbuddy.utils;

import java.util.ArrayList;

public class MiscUtils {

    public static ArrayList<String> basicAttributes() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("Body");
        tmp.add("Quickness");
        tmp.add("Strength");
        tmp.add("Charisma");
        tmp.add("Intelligence");
        tmp.add("Willpower");
        return tmp;
    }

    public static ArrayList<String> allAttributes() {
        ArrayList<String> tmp = basicAttributes();
        tmp.add("Reaction");
        tmp.add("Essence");
        tmp.add("Magic");

        return tmp;
    }
}
