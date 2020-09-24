package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillPriorityContainer;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class SkillsController {

    LinkedHashMap<String, SkillPriorityContainer> skillPriorityTable;
    LinkedHashMap<String, SkillContainer> skillList;
    LinkedHashMap<String, SkillContainer> selectedActiveSkills;
    LinkedHashMap<String, SkillContainer> selectedKnowledgeSkills;
    LinkedHashMap<String, SkillContainer> selectedLanguageSkills;
    LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> selectedSkillsLists;
    SkillPriorityContainer defaultPriority;
    private int baseSkillPoints;
    private SkillPriorityContainer selectedPriorityLevel;


    public SkillsController() {
        skillList = new LinkedHashMap<>();
        LoadTables();
        defaultPriority = new SkillPriorityContainer("E", 27);
        SetPriority(defaultPriority);
    }

    private void LoadTables() {
        skillPriorityTable = new LinkedHashMap<>();
        skillPriorityTable.put("A", new SkillPriorityContainer("A", 50));
        skillPriorityTable.put("B", new SkillPriorityContainer("B", 40));
        skillPriorityTable.put("C", new SkillPriorityContainer("C", 34));
        skillPriorityTable.put("D", new SkillPriorityContainer("D", 30));
        skillPriorityTable.put("E", new SkillPriorityContainer("E", 27) );

        selectedSkillsLists = new LinkedHashMap<>();

        selectedActiveSkills = new LinkedHashMap<>();
        selectedSkillsLists.put("Active Skills", selectedActiveSkills);

        selectedKnowledgeSkills = new LinkedHashMap<>();
        selectedSkillsLists.put("Knowledge Skills", selectedKnowledgeSkills);

        selectedLanguageSkills = new LinkedHashMap<>();
        selectedSkillsLists.put("Language Skills", selectedLanguageSkills);
    }

    public ComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        box.addElement("-- Select Skills Level --");
        for (Map.Entry<String, SkillPriorityContainer> skill: skillPriorityTable.entrySet()) {
            box.addElement(String.format("%s - %d Points", skill.getValue().getPriorityLevel(), skill.getValue().getSkillPoints()));
        }
        return box;
    }

    public void addNewSkill(SkillContainer baseSkill, String title) {
        String skillType = baseSkill.getSkillType();
        LinkedHashMap<String, SkillContainer> list = selectedSkillsLists.get(skillType);

        System.out.println("Skill Level: " + baseSkill.getSkillLevel());

        for (Map.Entry<String, SkillContainer> skill: list.entrySet()) {
            if (skill.getValue() == baseSkill) {
                System.out.println("REMOVED!");
                list.remove(skill.getKey());
            }
        }

        //If they leave the level at 0 or try to spec with only one point in just remove it
        if (baseSkill.getSkillLevel() <= 0) {
            System.out.println("ERROR: NOTHING HERE");
            return;
        }

        System.out.println("COMPLETE!");
        list.put(title, baseSkill);

    }

    public LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> getSelectedSkillsLists() {
        return selectedSkillsLists;
    }

    public int getBaseSkillPoints() {
        return baseSkillPoints;
    }

    public void setBaseSkillPoints() {
        baseSkillPoints = selectedPriorityLevel.getSkillPoints();
    }

    private void SetPriority(SkillPriorityContainer priorityLevel) {
        selectedPriorityLevel = priorityLevel;
        setBaseSkillPoints();
    }

    public void setSkillPriority(String string) {
        if (string.startsWith("--")) {
            SetPriority(defaultPriority);
        } else {
            String searchTerm = String.valueOf(string.charAt(0));
            SetPriority(skillPriorityTable.get(searchTerm));
        }
    }
}
