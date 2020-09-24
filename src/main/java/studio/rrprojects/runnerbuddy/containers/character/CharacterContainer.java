package studio.rrprojects.runnerbuddy.containers.character;

import studio.rrprojects.runnerbuddy.controllers.*;

import java.io.*;

public class CharacterContainer {

    DescriptionController descriptionController;
    FileController fileController;
    ContactsController contactsController;
    RaceController raceController;
    AttributeController attributeController;
    SkillsController skillsController;
    MagicController magicController;
    ResourceController resourceController;

    public CharacterContainer() {

        //Start the Controllers
        descriptionController = new DescriptionController();
        raceController = new RaceController();
        attributeController = new AttributeController();
        skillsController = new SkillsController();
        magicController = new MagicController();
        resourceController = new ResourceController();
        contactsController = new ContactsController();

        fileController = new FileController();

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


        try {
            writer.write(descriptionBlock);
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

}

