package studio.rrprojects.runnerbuddy.constants;

import java.io.File;

public class FileConstants {
    public static final String MAIN_DIRECTORY = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "RunnerBuddy" + File.separator;
    public static final String CHARACTER_DIRECTORY =  MAIN_DIRECTORY + "Characters" + File.separator;

    //Resource Directories
    public static final String RESOURCE_JSON_DIR = "/JSON" + File.separator;

    public static final String RESOURCE_SKILLS = RESOURCE_JSON_DIR+ "Skills" + File.separator;
    public static final String RESOURCE_MISC = RESOURCE_JSON_DIR + "MISC" + File.separator;
    public static final String RESOURCE_CHARACTER = RESOURCE_JSON_DIR + "Character" + File.separator;
    public static final String RESOURCE_GEAR = RESOURCE_JSON_DIR + "Gear" + File.separator;
}
