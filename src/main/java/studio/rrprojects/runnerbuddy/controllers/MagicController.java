package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.priority.ListPriority;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MagicController extends ControllerClass{
    private final CharacterContainer characterContainer;
    private boolean isMagical = false;
    private ArrayList<String> magicalOptions;
    private ListPriority selectedPriority;
    private LinkedHashMap<String, Object> magicTypeMap = new LinkedHashMap<>();

    public MagicController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        CreateMagicTypeMap();
    }

    private void CreateMagicTypeMap() {

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
    public void setSelectedPriority(PriorityContainer priorityContainer) {
        selectedPriority = (ListPriority) priorityContainer;
        magicalOptions = selectedPriority.getAvailableOptions();

        //Check for if magical
        System.out.println("MAGIC CONTROLLER : CHECKING IF MAGICAL");
        if (!magicalOptions.contains("Mundane")) {
            setMagical(true);
        }
    }

    public ArrayList<String> getMagicalOptions() {
        return magicalOptions;
    }
}
