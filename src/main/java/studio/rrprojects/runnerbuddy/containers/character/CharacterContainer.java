package studio.rrprojects.runnerbuddy.containers.character;

import studio.rrprojects.runnerbuddy.controllers.*;
import studio.rrprojects.runnerbuddy.controllers.gear.GearController;

import java.io.*;

public class CharacterContainer {

    private final PriorityController priorityController;
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
        raceController = new RaceController(this);
        attributeController = new AttributeController(this);
        skillsController = new SkillsController(this);
        magicController = new MagicController(this);
        resourceController = new ResourceController(this);
        //descriptionController = new DescriptionController(this);
        //gearController = new GearController(this);
        //contactsController = new ContactsController(this);
        priorityController = new PriorityController(this);
    }



    public PriorityController getPriorityController() { return priorityController; }

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

