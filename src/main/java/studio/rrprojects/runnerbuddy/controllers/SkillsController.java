package studio.rrprojects.runnerbuddy.controllers;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillPriorityContainer;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SkillsController {

    private final CharacterContainer characterContainer;
    LinkedHashMap<String, SkillPriorityContainer> skillPriorityTable;
    LinkedHashMap<String, SkillContainer> skillList;
    LinkedHashMap<String, SkillContainer> selectedActiveSkills, tableActiveSkills, selectedKnowledgeSkills, tableKnowledgeSkills, selectedLanguageSkills, tableLanguageSkills;
    LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> selectedSkillsLists;
    SkillPriorityContainer defaultPriority;
    private int baseSkillPoints;
    private SkillPriorityContainer selectedPriorityLevel;




    public SkillsController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        skillList = new LinkedHashMap<>();
        LoadTables();
        defaultPriority = new SkillPriorityContainer("E", 27);
        SetPriority(defaultPriority);

        LoadSkillJSON();
    }

    private void LoadSkillJSON() {

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
        tableActiveSkills = LoadSkillList("Active Skills", "SR3E_active_skills.json");

        selectedKnowledgeSkills = new LinkedHashMap<>();
        selectedSkillsLists.put("Knowledge Skills", selectedKnowledgeSkills);
        tableKnowledgeSkills = LoadSkillList("Knowledge Skills", "SR3E_knowledge_skills.json");

        selectedLanguageSkills = new LinkedHashMap<>();
        selectedSkillsLists.put("Language Skills", selectedLanguageSkills);
        tableLanguageSkills = LoadSkillList("Language Skills", "SR3E_language_skills.json");

    }

    private LinkedHashMap<String, SkillContainer> LoadSkillList(String skillType, String fileName) {
        LinkedHashMap<String, SkillContainer> tmp = new LinkedHashMap<>();

        String filePath = characterContainer.getFileController().getSkillsDirectory() + File.separator + fileName;

        ArrayList<SkillContainer> tmpArray = CreateArrayFromJSON(filePath, skillType);
        assert tmpArray != null;
        for (SkillContainer skill: tmpArray) {
            System.out.println("SKILLS - LOADING: " + skill.getSkillName());
            tmp.put(skill.getSkillName(), skill);
        }

        return tmp;
    }

    private ArrayList<SkillContainer> CreateArrayFromJSON(String filePath, String skillType) {
        ArrayList<SkillContainer> arrayList = new ArrayList<>();
        JsonObject mainObj;

        try {
            FileReader reader = new FileReader(new File(filePath));
            mainObj = (JsonObject) Json.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        for (JsonObject.Member skill : mainObj) {
            arrayList.add(new SkillContainer(skill, skillType));
        }

        return arrayList;
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

    public String getFinalText() {
        StringBuilder output = new StringBuilder("- - - SKILLS - - -\n");
        for (Map.Entry<String, LinkedHashMap<String, SkillContainer>> skillList: selectedSkillsLists.entrySet()) {
            output.append(" - ").append(skillList.getKey()).append(" -\n");
            for (Map.Entry<String, SkillContainer> skill: skillList.getValue().entrySet()) {
                output.append(skill.getKey()).append("\n");
            }
        }
        return output.toString();
    }

    public LinkedHashMap<String, SkillContainer> getTableActiveSkills() {
        return tableActiveSkills;
    }

    public LinkedHashMap<String, SkillContainer> getTableKnowledgeSkills() {
        return tableKnowledgeSkills;
    }

    public LinkedHashMap<String, SkillContainer> getTableLanguageSkills() {
        return tableLanguageSkills;
    }

    public DefaultListModel<String> TableToList(LinkedHashMap<String, SkillContainer> table, Boolean sortByAttribute) {
        DefaultListModel<String> list = new DefaultListModel<>();

        LinkedHashMap<String, ArrayList<SkillContainer>> categoryList = new LinkedHashMap<>();

        if (sortByAttribute) {
            for (Map.Entry<String, SkillContainer> skill: table.entrySet()) {
                if (!categoryList.containsKey(skill.getValue().getAttribute())) {
                    categoryList.put(skill.getValue().getAttribute(), new ArrayList<>());
                }
                categoryList.get(skill.getValue().getAttribute()).add(skill.getValue());
            }
        } else {
            for (Map.Entry<String, SkillContainer> skill: table.entrySet()) {
                if (!categoryList.containsKey(skill.getValue().getCategory())) {
                    categoryList.put(skill.getValue().getCategory(), new ArrayList<>());
                }
                categoryList.get(skill.getValue().getCategory()).add(skill.getValue());
            }
        }

        for (Map.Entry<String, ArrayList<SkillContainer>> category: categoryList.entrySet()) {

            list.addElement("-- " + TextUtils.TitleCase(category.getKey()) + " --" );
            for (SkillContainer skill: category.getValue()) {
                list.addElement(skill.getSkillName());
            }
        }

        return list;
    }

    public SkillContainer getSkill(String skill, String skillCategory) {
        if (skillCategory.equalsIgnoreCase("Active Skills")) {
            return tableActiveSkills.get(skill);
        } else if (skillCategory.equalsIgnoreCase("Knowledge Skills")) {
            return tableKnowledgeSkills.get(skill);
        } else if (skillCategory.equalsIgnoreCase("Language Skills")) {
            return tableLanguageSkills.get(skill);
        } else {
            return null;
        }
    }
}
