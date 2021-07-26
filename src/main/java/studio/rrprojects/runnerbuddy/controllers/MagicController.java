package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.constants.MagicConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.magic.MagicUserContainer;
import studio.rrprojects.runnerbuddy.containers.magic.SpellCasterContainer;
import studio.rrprojects.runnerbuddy.containers.priority.ListPriority;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MagicController extends ControllerClass{
    private final CharacterContainer characterContainer;
    private boolean isMagical = false;
    private ArrayList<String> magicalOptions;
    private ListPriority selectedPriority;
    private LinkedHashMap<String, MagicUserContainer> magicUserTypeMap = new LinkedHashMap<>();

    public MagicController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        ProcessMagicTypes();

    }

    private void ProcessMagicTypes() {
        //Right now, hard coding all this, might look into a JSON for it later.
        SpellCasterContainer fullMagician = new SpellCasterContainer(MagicConstants.FULL_MAGICIAN, 25);
        fullMagician.addHermeticOption(MagicConstants.FULL_MAGE);
        fullMagician.addHermeticOption(MagicConstants.FULL_SHAMAN);

        SpellCasterContainer aspectedMagician = new SpellCasterContainer(MagicConstants.ASPECTED_MAGICIAN, 25);
        aspectedMagician.addHermeticOption(MagicConstants.ELEMENTALIST);
        aspectedMagician.addHermeticOption(MagicConstants.MAGE_CONJURER);
        aspectedMagician.addHermeticOption(MagicConstants.MAGE_SORCERER);
        aspectedMagician.addHermeticOption(MagicConstants.SHAMINIST);
        aspectedMagician.addHermeticOption(MagicConstants.SHAMAN_CONJURER);
        aspectedMagician.addHermeticOption(MagicConstants.SHAMAN_SORCERER);

        magicUserTypeMap.put(MagicConstants.FULL_MAGICIAN, fullMagician);
        magicUserTypeMap.put(MagicConstants.ASPECTED_MAGICIAN, aspectedMagician);
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

    @Override
    public ListPriority getSelectedPriority() {
        return selectedPriority;
    }

    public LinkedHashMap<String, MagicUserContainer> getMagicUserTypeMap() {
        return magicUserTypeMap;
    }
}
