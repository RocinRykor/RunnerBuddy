package studio.rrprojects.runnerbuddy.constants;

import java.io.File;

public class FileConstants {
    public static final String MAIN_DIRECTORY = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "RunnerBuddy" + File.separator;
    public static final String CHARACTER_DIRECTORY =  MAIN_DIRECTORY + "Characters" + File.separator;

    //Resource Directories
    public static final String RESOURCE_SKILLS =  File.separator +"JSON" + File.separator + "Skills" + File.separator;
    public static final String RESOURCE_MISC =  File.separator +"JSON" + File.separator + "MISC" + File.separator;
    public static final String RESOURCE_CHARACTER =  File.separator +"JSON" + File.separator + "Character" + File.separator;
}
