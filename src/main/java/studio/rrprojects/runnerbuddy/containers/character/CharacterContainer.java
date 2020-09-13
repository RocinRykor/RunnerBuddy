package studio.rrprojects.runnerbuddy.containers.character;

import studio.rrprojects.runnerbuddy.controllers.*;

public class CharacterContainer {

    ContactsController contactsController;
    RaceController raceController;
    AttributeController attributeController;
    SkillsController skillsController;
    MagicController magicController;
    ResourceController resourceController;

    public CharacterContainer() {
        //Start the Controllers
        raceController = new RaceController();
        attributeController = new AttributeController();
        skillsController = new SkillsController();
        magicController = new MagicController();
        resourceController = new ResourceController();
        contactsController = new ContactsController();
    }

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

