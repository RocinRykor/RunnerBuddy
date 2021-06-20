package studio.rrprojects.runnerbuddy.containers.character;

import studio.rrprojects.runnerbuddy.controllers.*;
import studio.rrprojects.runnerbuddy.misc.PriorityObject;
import studio.rrprojects.runnerbuddy.misc.ValidChecker;

import java.util.LinkedHashMap;

public class CharacterContainer {

    private final PriorityController priorityController;
    private final AttributeController attributeController;
    private final RaceController raceController;
    private final MagicController magicController;
    private final DescriptionController descriptionController;

    public CharacterContainer() {

        //Start the Controllers | Uses "this" so that each controller can access the others through the CharacterContainer
        priorityController = new PriorityController(this);
        attributeController = new AttributeController(this);
        raceController = new RaceController(this);
        magicController = new MagicController(this);
        descriptionController = new DescriptionController(this);

        //skillsController = new SkillsController(this);
        //resourceController = new ResourceController(this);

        //gearController = new GearController(this);
        //contactsController = new ContactsController(this);

    }

    public void ProcessPriorityMap(LinkedHashMap<String, PriorityObject> priorityMap) {
        raceController.setAvailibleRaces(priorityMap.get("Race"));
        attributeController.setSelectedPriority(priorityMap.get("Attributes"));
        magicController.setSelectedPriority(priorityMap.get("Magic"));

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

    public ValidChecker ValidCheck() {
        ValidChecker validChecker = new ValidChecker();
        validChecker.ProcessCharacter(this);
        return validChecker;
    }
}

