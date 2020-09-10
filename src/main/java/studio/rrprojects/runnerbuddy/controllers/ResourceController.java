package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.resources.ResourcePriorityContainer;

import javax.swing.*;
import java.util.ArrayList;

public class ResourceController {

    ArrayList<ResourcePriorityContainer> resourcePriorityTable;

    public ResourceController(){
        LoadTables();
    }

    private void LoadTables() {
        resourcePriorityTable = new ArrayList<>();
        resourcePriorityTable.add(new ResourcePriorityContainer("A", 1000000));
        resourcePriorityTable.add(new ResourcePriorityContainer("B", 400000));
        resourcePriorityTable.add(new ResourcePriorityContainer("C", 90000));
        resourcePriorityTable.add(new ResourcePriorityContainer("D", 20000));
        resourcePriorityTable.add(new ResourcePriorityContainer("E", 5000));
    }

    public ComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        box.addElement("-- Select Resources Level --");
        for (ResourcePriorityContainer money: resourcePriorityTable) {
            box.addElement(String.format("%s - %s¥", money.getPriorityLevel(), money.getNuyenAmountString()));
        }
        return box;
    }
}
