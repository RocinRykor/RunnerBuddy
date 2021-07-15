package studio.rrprojects.runnerbuddy.containers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.utils.JsonUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SkillContainer{

    private String skillType;
    private String skillName;
    private String attribute;
    private boolean isBuildRepairAvailible;
    private String defaults;
    private String description;
    private String source;
    private ArrayList<String> availableSpecializations;
    private String category;
    private int skillLevel;
    private ArrayList<SpecializationObject> selectedSpecializations;

    public SkillContainer(String name, JSONObject skill, String type) {
        skillName = TextUtils.titleCase(name);
        skillType = TextUtils.titleCase(type);
        
        attribute = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "attribute", "Intelligence"));
        isBuildRepairAvailible = JsonUtils.getBoolOrDefault(skill, "build_repair", false);
        defaults = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "defaults", "None"));
        description = skill.getString("description");
        source = skill.getString("source");

        String specializationString = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "specialization", ""));
        availableSpecializations = processSpecializationString(specializationString);
        System.out.println("Specializtions: " + availableSpecializations);

        category = TextUtils.titleCase(skill.getString("category"));

        selectedSpecializations = new ArrayList<>();

        skillLevel = JsonUtils.getIntOrDefault(skill, "value", 0);

        System.out.println("Skill Loaded: " + skillName);
    }

    public SkillContainer() {
    }

    private ArrayList<String> processSpecializationString(String string) {
        ArrayList<String> tmp = new ArrayList<>();

        String[] splitString = string.split(", ");

        if (splitString[0].length() > 0) {
            for (String s: splitString) {
                tmp.add(s);
            }
        }
        tmp.add("-> Custom");
        return tmp;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public boolean isBuildRepairAvailible() {
        return isBuildRepairAvailible;
    }

    public void setBuildRepairAvailible(boolean buildRepairAvailible) {
        isBuildRepairAvailible = buildRepairAvailible;
    }

    public String getDefaults() {
        return defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public ArrayList<String> getAvailableSpecializations() {
        return availableSpecializations;
    }

    public void setAvailableSpecializations(ArrayList<String> availableSpecializations) {
        this.availableSpecializations = availableSpecializations;
    }

    public ArrayList<SpecializationObject> getSelectedSpecializations() {
        return selectedSpecializations;
    }

    public void setSelectedSpecializations(ArrayList<SpecializationObject> selectedSpecializations) {
        this.selectedSpecializations = selectedSpecializations;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public DefaultTableModel getTableDiscription() {
        String col[] = {"1", "2"};

        DefaultTableModel tableModel = new MyDefaultTableModel(col, 0);

        //I am adding blank rows because I can't figure out the damn spacing for cells

        tableModel.addRow(addTableRow("Skill Name", skillName));
        tableModel.addRow(addBlankRow());
        tableModel.addRow(addTableRow("Category", category));
        tableModel.addRow(addBlankRow());
        tableModel.addRow(addTableRow("Attribute", attribute));
        tableModel.addRow(addBlankRow());
        tableModel.addRow(addTableRow("B/R", TextUtils.titleCase(String.valueOf(isBuildRepairAvailible))));
        tableModel.addRow(addBlankRow());
        tableModel.addRow(addTableRow("Defaults:", defaults));
        tableModel.addRow(addBlankRow());
        tableModel.addRow(addTableRow("Source", source));
        tableModel.addRow(addBlankRow());
        tableModel.addRow(addTableRow("Description", description));

        return tableModel;
    }

    private Object[] addBlankRow() {
        return new String[]{"", ""};
    }

    private Object[] addTableRow(Object o1, Object o2) {
        return new Object[]{o1, o2};
    }

    private class SpecializationObject {
        private String specializationName;
        private int skillLevel;

        public String getSpecializationName() {
            return specializationName;
        }

        public void setSpecializationName(String specializationName) {
            this.specializationName = specializationName;
        }

        public int getSkillLevel() {
            return skillLevel;
        }

        public void setSkillLevel(int skillLevel) {
            this.skillLevel = skillLevel;
        }
    }

    @Override
    public String toString() {
        return skillName;
    }

    private class MyDefaultTableModel extends DefaultTableModel {
        public MyDefaultTableModel(String[] col, int i) {
            super(col, i);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
