package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;

import java.util.ArrayList;

public class PriorityController {
    private final CharacterContainer characterContainer;
    private ArrayList<PriorityGroup> listPriorityGroup;

    public PriorityController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        
        CreatePriorityGroups();
    }

    private void CreatePriorityGroups() {
        //Each Individual priority group is managed by it's own controller and imported here
        listPriorityGroup = new ArrayList<>();
        listPriorityGroup.add(characterContainer.getRaceController().getPriorityGroup());
        listPriorityGroup.add(characterContainer.getMagicController().getPriorityGroup());
        listPriorityGroup.add(characterContainer.getAttributeController().getPriorityGroup());
        listPriorityGroup.add(characterContainer.getSkillsController().getPriorityGroup());
        listPriorityGroup.add(characterContainer.getResourceController().getPriorityGroup());
    }

    public ArrayList<PriorityGroup> getListPriorityGroup() {
        return listPriorityGroup;
    }

    public PriorityGroup getPriorityGroupByKeyword(String keyword) {
        for (PriorityGroup priorityGroup : listPriorityGroup) {
            if (priorityGroup.getKeyword().equalsIgnoreCase(keyword)) {
                return priorityGroup;
            }
        }
        return null;
    }
}
