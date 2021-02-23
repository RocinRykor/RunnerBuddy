package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.magic.MagicPriorityContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MagicController {
    private PriorityGroup priorityGroup;

    public MagicController(CharacterContainer characterContainer){

    GeneratePriorityGroup();
}

    private void GeneratePriorityGroup() {
        priorityGroup = new PriorityGroup("Magic");
        priorityGroup.addOption(1, "A", "Full Magician");
        priorityGroup.addOption(2, "B", "Aspected Magician");
        priorityGroup.addOption(3, "B", "Adept");
        priorityGroup.addOption(4, "D", "Mundane");
        priorityGroup.addOption(5, "E", "Mundane");
    }


    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }
}
