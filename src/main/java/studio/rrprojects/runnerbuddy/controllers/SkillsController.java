package studio.rrprojects.runnerbuddy.controllers;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillPriorityContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SkillsController {

    private PriorityGroup priorityGroup;

    public SkillsController(CharacterContainer characterContainer) {
        GeneratePriorityGroup();
    }

    private void GeneratePriorityGroup() {
        priorityGroup = new PriorityGroup("Skills");
        priorityGroup.addOption(1, "A", "50 Points", 50);
        priorityGroup.addOption(2, "B", "40 Points", 40);
        priorityGroup.addOption(3, "C", "34 Points", 34);
        priorityGroup.addOption(4, "D", "30 Points", 30);
        priorityGroup.addOption(5, "E", "27 Points", 27);
    }


    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }
}
