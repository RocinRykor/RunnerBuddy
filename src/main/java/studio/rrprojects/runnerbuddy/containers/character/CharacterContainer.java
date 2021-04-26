package studio.rrprojects.runnerbuddy.containers.character;

import studio.rrprojects.runnerbuddy.controllers.*;
import studio.rrprojects.runnerbuddy.controllers.gear.GearController;

import java.io.*;

public class CharacterContainer {

    private final PriorityController priorityController;

    public CharacterContainer() {

        //Start the Controllers | Uses "this" so that each controller can access the others through the CharacterContainer
        //TODO Enable These as needed
        priorityController = new PriorityController(this);

        //raceController = new RaceController(this);
        //attributeController = new AttributeController(this);
        //skillsController = new SkillsController(this);
        //magicController = new MagicController(this);
        //resourceController = new ResourceController(this);
        //descriptionController = new DescriptionController(this);
        //gearController = new GearController(this);
        //contactsController = new ContactsController(this);

    }


    public PriorityController getPriorityController() {
        return priorityController;
    }
}

