package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;
import studio.rrprojects.runnerbuddy.containers.resources.ResourcePriorityContainer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResourceController {
    private final CharacterContainer characterContainer;
    LinkedHashMap<String, ResourcePriorityContainer> resourcePriorityTable;
    ResourcePriorityContainer selectedPriority, defaultPriority;

    public ResourceController(CharacterContainer characterContainer){
        this.characterContainer = characterContainer;
        LoadTables();
        defaultPriority = resourcePriorityTable.get("E");
        SetPriority(defaultPriority);
    }

    private void SetPriority(ResourcePriorityContainer priorityContainer) {
        selectedPriority = priorityContainer;
    }

    private void LoadTables() {
        resourcePriorityTable = new LinkedHashMap<>();
        resourcePriorityTable.put("A", new ResourcePriorityContainer("A", 1000000));
        resourcePriorityTable.put("B", new ResourcePriorityContainer("B", 400000));
        resourcePriorityTable.put("C", new ResourcePriorityContainer("C", 90000));
        resourcePriorityTable.put("D", new ResourcePriorityContainer("D", 20000));
        resourcePriorityTable.put("E", new ResourcePriorityContainer("E", 5000));
    }

    public ComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        box.addElement("-- Select Resources Level --");
        for (Map.Entry<String, ResourcePriorityContainer> money: resourcePriorityTable.entrySet()) {
            box.addElement(String.format("%s - %s¥", money.getValue().getPriorityLevel(), money.getValue().getNuyenAmountString()));
        }
        return box;
    }

    public void setSelectedResourcePriorityLevel(String string) {
        if (string.startsWith("--")) {
            SetPriority(defaultPriority);
        } else {
            String searchTerm = String.valueOf(string.charAt(0));
            SetPriority(resourcePriorityTable.get(searchTerm));
        }
    }

    public String getBaseResources() {
        return selectedPriority.getNuyenAmountString();
    }

    public int getRemainingResources() {
        int availableMoney = selectedPriority.getNuyenAmount();

        for (Map.Entry<String, GearContainer> gearItem: characterContainer.getGearController().getSelectedGearList().entrySet()) {
            availableMoney -= gearItem.getValue().getTotalCost();
        }

        return availableMoney;
    }
}
