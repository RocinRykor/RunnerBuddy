package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;
import studio.rrprojects.runnerbuddy.containers.resources.ResourcePriorityContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResourceController {
    private PriorityGroup priorityGroup;

    public ResourceController(CharacterContainer characterContainer){

        GeneratePriorityGroup();
    }

    private void GeneratePriorityGroup() {
        priorityGroup = new PriorityGroup("Resources");
        priorityGroup.addOption(1, "A", "1,000,000¥", 1000000);
        priorityGroup.addOption(2, "B", "400,000¥", 400000);
        priorityGroup.addOption(3, "B", "90,000¥", 90000);
        priorityGroup.addOption(4, "D", "20,000¥", 20000);
        priorityGroup.addOption(5, "E", "5,000¥", 5000);
    }


    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }
}
