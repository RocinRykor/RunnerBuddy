package studio.rrprojects.runnerbuddy.containers.priority;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.constants.PriorityConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.components.PriorityModule;
import studio.rrprojects.runnerbuddy.utils.JsonUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PriorityLevelGroup {

    private final String priorityLevel;
    private final JSONObject jsonObect;
    private LinkedHashMap<String, PriorityContainer> priorityContainerMap = new LinkedHashMap<>();
    private LinkedHashMap<String, PriorityContainer> popupMap = new LinkedHashMap<>();
    private PriorityContainer selectedPriority;
    private PriorityModule parent;

    public PriorityLevelGroup(String priorityLevel, JSONObject jsonObject) {
        this.priorityLevel = priorityLevel;
        this.jsonObect = jsonObject;

        //I am doing this manually becuase the priority table should never change
        //Race
        ListPriority racePriority = new ListPriority(priorityLevel, PriorityConstants.RACE, JsonUtils.getObjOrDefault(jsonObject, "race", null));
        priorityContainerMap.put(PriorityConstants.RACE, racePriority);
        popupMap.put(racePriority.toString(), racePriority);

        //Magic
        ListPriority magicPriority = new ListPriority(priorityLevel, PriorityConstants.MAGIC, jsonObject.get("magic"));
        priorityContainerMap.put(PriorityConstants.MAGIC, magicPriority);
        popupMap.put(magicPriority.toString(), magicPriority);

        //Attributes
        PointPriority attributePrioty = new PointPriority(priorityLevel, PriorityConstants.ATTRIBUTES, jsonObject.get("attributes"));
        priorityContainerMap.put(PriorityConstants.ATTRIBUTES, attributePrioty);
        popupMap.put(attributePrioty.toString(), attributePrioty);

        //Skills
        PointPriority skillsPriority = new PointPriority(priorityLevel, PriorityConstants.SKILLS, jsonObject.get("skills"));
        priorityContainerMap.put(PriorityConstants.SKILLS, skillsPriority);
        popupMap.put(skillsPriority.toString(), skillsPriority);

        //Rescources
        ResourcePriority resourcePriority = new ResourcePriority(priorityLevel, PriorityConstants.RESOURCES, jsonObject.get("resources"));
        priorityContainerMap.put(PriorityConstants.RESOURCES, resourcePriority);
        popupMap.put(resourcePriority.toString(), resourcePriority);
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public LinkedHashMap<String, PriorityContainer> getPriorityContainerMap() {
        return priorityContainerMap;
    }

    public JSONObject getJsonObect() {
        return jsonObect;
    }

    public LinkedHashMap<String, PriorityContainer> getPopupMap() {
        return popupMap;
    }

    public ArrayList<JMenuItem> getPopupMapAsMenuItems(){
        ArrayList<JMenuItem> tmp = new ArrayList<>();

        for (String priorityKey: popupMap.keySet()) {
            PriorityContainer priorityContainer = popupMap.get(priorityKey);

            if (priorityContainer.getValueRaw() != null) {
                JMenuItem item = new JMenuItem(priorityKey);

                //Here is where we search to see if we need to disable it
                if (selectedPriority != priorityContainer) {
                    ArrayList<PriorityContainer> listOfTakenPriorities = parent.getParent().getCharacterContainer().getPriorityController().getTakenPriorities();
                    String searchTerm = priorityContainer.getPriorityCategory();

                    for (PriorityContainer takenPriority : listOfTakenPriorities) {
                        if (takenPriority.getPriorityCategory().equalsIgnoreCase(searchTerm)) {
                            item.setEnabled(false);
                        }
                    }

                }

                item.addActionListener(actionEvent ->
                        SelectPriority(priorityContainer));


                tmp.add(item);
            }

        }

        return tmp;
    }

    private void SelectPriority(PriorityContainer priorityContainer) {
        String debugString = "SELECT_PRIORITY: ";

        System.out.println(debugString + priorityContainer);

        CharacterContainer characterContainer = parent.getParent().getCharacterContainer();

        //Check if anything changed
        if (selectedPriority != null && selectedPriority != priorityContainer) {
            //Remove the old priority from the taken list
            characterContainer.getPriorityController().removeTakenPriority(selectedPriority);
        }

        //Denote the skill as selected here
        selectedPriority = priorityContainer;

        //Update the Priority Controller
        characterContainer.getPriorityController().addTakenPriority(priorityContainer);

        parent.Update();
    }

    public PriorityContainer getSelectedPriority() {
        return selectedPriority;
    }

    public void setParent(PriorityModule parent) {
        this.parent = parent;
    }

    public PriorityModule getParent() {
        return parent;
    }

    public void ClearSelection() {
        String debugString = "CLEAR_SELECTION: ";

        System.out.println(debugString + selectedPriority);

        if (selectedPriority != null) {
            CharacterContainer characterContainer = parent.getParent().getCharacterContainer();
            characterContainer.getPriorityController().removeTakenPriority(selectedPriority);
            selectedPriority = null;
            parent.Update();
        }
    }

    public void setSelectionByCategory(String category) {
        SelectPriority(priorityContainerMap.get(category));
    }
}
