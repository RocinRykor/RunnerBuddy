package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.constants.JsonFileConstants;
import studio.rrprojects.runnerbuddy.constants.PriorityConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityLevelGroup;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PriorityController extends ControllerClass{
    private final CharacterContainer characterContainer;
    private final HashMap<String, PriorityLevelGroup> masterPriorityMap = new HashMap<>();
    private final String[] listCategory = PriorityConstants.PRIORITIES;
    private JSONObject priorityJson;
    private ArrayList<PriorityContainer> takenPriorities = new ArrayList<>();

    public PriorityController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        LoadPriorityFile();
        PopulatePriorityMap();
    }


    private void PopulatePriorityMap() {
        priorityJson.keySet().forEach(priorityLevelKey -> {
            String titlecaseKey = priorityLevelKey.toUpperCase();

            PriorityLevelGroup priorityLevelGroup = new PriorityLevelGroup(titlecaseKey, priorityJson.getJSONObject(priorityLevelKey));

            masterPriorityMap.put(titlecaseKey, priorityLevelGroup);
        });
    }

    private void LoadPriorityFile() {

        InputStream is = getClass().getResourceAsStream("/JSON/Misc/" + JsonFileConstants.PRIORITY_TABLE);
        
        assert is != null;
        JSONTokener token = new JSONTokener(is);

        priorityJson = new JSONObject(token);
    }


    public PriorityLevelGroup getOptionsByLevel(String priorityKey) {
        return masterPriorityMap.get(priorityKey);
    }

    public String[] getListCategory() {
        return listCategory;
    }

    public HashMap<String, PriorityLevelGroup> getMasterPriorityMap() {
        return masterPriorityMap;
    }

    public void addTakenPriority(PriorityContainer priorityContainer) {
        takenPriorities.add(priorityContainer);
    }

    public ArrayList<PriorityContainer> getTakenPriorities() {
        return takenPriorities;
    }

    public void removeTakenPriority(PriorityContainer priorityContainer) {
        if (takenPriorities.contains(priorityContainer)) {
            takenPriorities.remove(priorityContainer);
        } else {
            System.out.println("PRIORITY CONTROLLER ERROR: CANNNOT REMOVE " + priorityContainer);
        }
    }

    public PriorityContainer getPriorityByCategory(String categoryKey) {
        for (PriorityContainer priorityContainer: takenPriorities) {
            if (priorityContainer.getPriorityCategory().equalsIgnoreCase(categoryKey)) {
                return priorityContainer;
            }
        }

        System.out.println("PRIORITY CONTROLLER ERROR: \"" + categoryKey + "\" NOT FOUND");
        return null;
    }

    public String getPriorityMapString() {
        String output = "- Priority Map -\n";

        for (PriorityContainer priorityContainer: takenPriorities) {
            output += priorityContainer.toMapDisplay() + "\n";
        }

        return output;
    }

    public void Sort() {
        Collections.sort(takenPriorities);
    }
}
