package studio.rrprojects.runnerbuddy.controllers;

import java.io.File;
import java.util.ArrayList;

public class FileController {

    private final String mainDirectory;
    private final String jsonDirectory;
    private final String characterDirectory;
    private final String skillsDirectory;
    private final String gearDirectory;
    private final String magicDirectory;
    private final String cyberDirectory;
    private ArrayList<String> directoryList;

    public FileController() {
        //Main Directories
        mainDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "RunnerBuddy";
        jsonDirectory = mainDirectory + File.separator + "JSON";
        characterDirectory = mainDirectory + File.separator + "Characters";

        //Subdirectories
        skillsDirectory = jsonDirectory + File.separator + "Skills";
        gearDirectory = jsonDirectory + File.separator + "Gear";
        magicDirectory = jsonDirectory + File.separator + "Magic";
        cyberDirectory = jsonDirectory + File.separator + "Cyberware";

        InitArrayList();

        InitDirectories();
    }

    private void InitDirectories() {
        for (String directory: directoryList) {
            File dir = new File(directory);
            if (!dir.exists()) {
                if (dir.mkdir()) {
                    System.out.println("Creating Directory: " + directory);
                }
            }
        }
    }

    private void InitArrayList() {
        directoryList = new ArrayList<>();
        directoryList.add(mainDirectory);
        directoryList.add(jsonDirectory);
        directoryList.add(characterDirectory);
        directoryList.add(skillsDirectory);
        directoryList.add(gearDirectory);
        directoryList.add(magicDirectory);
        directoryList.add(cyberDirectory);
    }

    public String getMainDirectory() {
        return mainDirectory;
    }

    public String getJsonDirectory() {
        return jsonDirectory;
    }

    public String getCharacterDirectory() {
        return characterDirectory;
    }

    public String getSkillsDirectory() {
        return skillsDirectory;
    }

    public String getGearDirectory() {
        return gearDirectory;
    }

    public String getMagicDirectory() {
        return magicDirectory;
    }

    public String getCyberDirectory() {
        return cyberDirectory;
    }
}
