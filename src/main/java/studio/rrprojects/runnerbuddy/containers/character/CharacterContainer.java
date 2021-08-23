package studio.rrprojects.runnerbuddy.containers.character;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.constants.FileConstants;
import studio.rrprojects.runnerbuddy.constants.JsonFileConstants;
import studio.rrprojects.runnerbuddy.constants.PriorityConstants;
import studio.rrprojects.runnerbuddy.controllers.*;
import studio.rrprojects.runnerbuddy.misc.ValidChecker;
import studio.rrprojects.runnerbuddy.textbuilder.TextBuilder;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import java.io.*;

public class CharacterContainer {

    private final PriorityController priorityController;
    private final AttributeController attributeController;
    private final RaceController raceController;
    private final MagicController magicController;
    private final DescriptionController descriptionController;
    private final SkillsController skillsController;
    private final ResourceController resourceController;

    public CharacterContainer() {

        //Start the Controllers | Uses "this" so that each controller can access the others through the CharacterContainer
        priorityController = new PriorityController(this);
        attributeController = new AttributeController(this);
        raceController = new RaceController(this);
        magicController = new MagicController(this);
        resourceController = new ResourceController(this);

        descriptionController = new DescriptionController(this);
        skillsController = new SkillsController(this);
    }

    public void ProcessPriorities() {
        raceController.setAvailibleRaces(priorityController.getPriorityByCategory(PriorityConstants.RACE));
        attributeController.setSelectedPriority(priorityController.getPriorityByCategory(PriorityConstants.ATTRIBUTES));
        magicController.setSelectedPriority(priorityController.getPriorityByCategory(PriorityConstants.MAGIC));
        skillsController.setSelectedPriority(priorityController.getPriorityByCategory(PriorityConstants.SKILLS));
        resourceController.setSelectedPriority(priorityController.getPriorityByCategory(PriorityConstants.RESOURCES));
    }

    public PriorityController getPriorityController() {
        return priorityController;
    }

    public AttributeController getAttributeController() {
        return attributeController;
    }

    public RaceController getRaceController() {
        return raceController;
    }

    public MagicController getMagicController() { return magicController; }

    public DescriptionController getDescriptionController() { return descriptionController; }

    public SkillsController getSkillsController() {
        return skillsController;
    }

    public ResourceController getResourceController() {
        return resourceController;
    }

    public ValidChecker ValidCheck() {
        ValidChecker validChecker = new ValidChecker();
        validChecker.ProcessCharacter(this);
        return validChecker;
    }

    public void exportToText() {
        //Create the TextBuilder
        TextBuilder builder = new TextBuilder();

        descriptionController.toTextObject(builder);
        attributeController.toTextObject(builder);
        skillsController.toTextObject(builder);

        File file = new File(FileConstants.CHARACTER_DIRECTORY + "Test.txt");

        try {
            file.createNewFile()
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output = builder.build();
        System.out.println(output);

        try {
            System.out.println("WRITING FILE");
            Writer writer = new FileWriter(file);
            writer.write(output);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportToJSON() {
        //First create the json
        String jsonPath = FileConstants.RESOURCE_CHARACTER + JsonFileConstants.BLANK_CHARACTER;
        InputStream is = getClass().getResourceAsStream(jsonPath);
        JSONTokener token = null;
        if (is != null) {
            token = new JSONTokener(is);
        } else {
            System.err.println(jsonPath + ": INPUT STREAM IS NULL!");
        }

        assert token != null;
        JSONObject blankCharacter = new JSONObject(token);

        attributeController.toJSON(blankCharacter);

        //Write the file;
        String characterName = "Test Character";

        FileUtil.createDirAndLoadFile(FileConstants.CHARACTER_DIRECTORY, characterName + ".json");

        String filePath = FileConstants.CHARACTER_DIRECTORY + characterName + ".json";

        JSONUtil.WriteJsonToFile(blankCharacter, FileUtil.loadFileFromPath(filePath));
    }
}

