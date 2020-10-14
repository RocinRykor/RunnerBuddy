package studio.rrprojects.runnerbuddy.containers.character;

import studio.rrprojects.runnerbuddy.controllers.*;
import studio.rrprojects.runnerbuddy.controllers.gear.GearController;

import java.io.*;

public class CharacterContainer {

    DescriptionController descriptionController;
    FileController fileController;
    ContactsController contactsController;
    RaceController raceController;
    AttributeController attributeController;
    GearController gearController;
    SkillsController skillsController;
    MagicController magicController;
    ResourceController resourceController;

    public CharacterContainer() {

        //Start the Controllers | Uses "this" so that each controller can access the others through the CharacterContainer
        fileController = new FileController(this);
        descriptionController = new DescriptionController(this);
        raceController = new RaceController(this);
        attributeController = new AttributeController(this);
        skillsController = new SkillsController(this);
        gearController = new GearController(this);
        magicController = new MagicController(this);
        resourceController = new ResourceController(this);
        contactsController = new ContactsController(this);
    }

    public void ExportToText() {
        String fileName = fileController.getCharacterDirectory() + File.separator + descriptionController.getNameStreet() + ".txt";

        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Created New Text File: " + file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert writer != null;
        String descriptionBlock = descriptionController.getFinalText();
        String attributeBlock = attributeController.getFinalText();
        String skillsBlock = skillsController.getFinalText();


        try {
            writer.write(descriptionBlock);
            writer.append("\n\n").append(attributeBlock);
            writer.append("\n").append(skillsBlock);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DescriptionController getDescriptionController() { return descriptionController; }

    public FileController getFileController() { return fileController; }

    public AttributeController getAttributeController() {
        return attributeController;
    }

    public SkillsController getSkillsController() {
        return skillsController;
    }

    public RaceController getRaceController() {
        return raceController;
    }

    public MagicController getMagicController() {
        return magicController;
    }

    public ResourceController getResourceController() {
        return resourceController;
    }

    public ContactsController getContactsController() {
        return contactsController;
    }

    public GearController getGearController() { return gearController; }
}

