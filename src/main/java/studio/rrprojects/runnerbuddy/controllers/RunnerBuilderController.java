package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.ProgramWindow;

public class RunnerBuilderController {
    CharacterContainer character;
    ProgramWindow appWindow;
    AttributeController attributeController;
    SkillsController skillsController;

    public RunnerBuilderController() {
        System.out.println("STARTING!");

        //Create A Blank Character Container
        CharacterContainer character = new CharacterContainer();

        //Start the Program Window
        ProgramWindow appWindow = new ProgramWindow("RunnerBuddy v1", this, character);
    }

    public AttributeController getAttributeController() {
        return attributeController;
    }

    public void setAttributeController(AttributeController attributeController) {
        this.attributeController = attributeController;
    }

    public SkillsController getSkillsController() {
        return skillsController;
    }

    public void setSkillsController(SkillsController skillsController) {
        this.skillsController = skillsController;
    }
}
