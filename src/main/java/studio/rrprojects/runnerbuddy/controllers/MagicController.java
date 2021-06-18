package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityObject;

public class MagicController extends ControllerClass{
    private final CharacterContainer characterContainer;
    private boolean isMagical = false;

    public MagicController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
    }

    public CharacterContainer getCharacterContainer() {
        return characterContainer;
    }

    public boolean isMagical() {
        return isMagical;
    }

    public void setMagical(boolean magical) {
        isMagical = magical;
    }

    @Override
    public void setSelectedPriority(PriorityObject priorityObject) {
        super.setSelectedPriority(priorityObject);

        //Check for if magical
        System.out.println("MAGIC CONTROLLER : CHECKING IF MAGICAL");
        if (!priorityObject.getDisplayString().equalsIgnoreCase("Mundane")) {
            setMagical(true);
        }
    }
}
