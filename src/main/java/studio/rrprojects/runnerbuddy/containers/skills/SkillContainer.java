package studio.rrprojects.runnerbuddy.containers.skills;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.constants.AttributeConstants;
import studio.rrprojects.runnerbuddy.utils.JsonUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SkillContainer{

    private final String skillBaseName;
    private String skillType;
    private String skillName;
    private String baseAttribute;
    private String linkedAttribute;
    private boolean isBuildRepairAvailible;
    private String defaults;
    private String description;
    private String source;
    private ArrayList<String> availableSpecializations;
    private String category;
    private int baseLevel;
    private int actualLevel;
    private ArrayList<SpecializationContainer> selectedSpecializations;

    public SkillContainer(String name, String type) {
        skillBaseName = TextUtils.titleCase(name);
        skillName = skillBaseName;
        skillType = TextUtils.titleCase(type);
    }

    public void processJSONObject(JSONObject skill) {
        baseAttribute = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "attribute", AttributeConstants.INTELLIGENCE));
        linkedAttribute = baseAttribute;

        isBuildRepairAvailible = JsonUtils.getBoolOrDefault(skill, "build_repair", false);

        defaults = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "defaults", "None"));
        description = skill.getString("description");
        source = skill.getString("source");

        String specializationString = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "specialization", ""));
        availableSpecializations = processSpecializationString(specializationString);
        //System.out.println("Specializtions: " + availableSpecializations);

        category = TextUtils.titleCase(skill.getString("category"));

        selectedSpecializations = new ArrayList<>();

        baseLevel = JsonUtils.getIntOrDefault(skill, "value", 1);

        //System.out.println("Skill Loaded: " + skillName);
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

    public String getSkillBaseName() {
        return skillBaseName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
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

    public int getBaseLevel() {
        return baseLevel;
    }

    public void setBaseLevel(int baseLevel) {
        this.baseLevel = baseLevel;
    }

    public int getActualLevel() {
        return actualLevel;
    }

    public void setActualLevel(int actualLevel) {
        this.actualLevel = actualLevel;
    }

    public String getBaseAttribute() {
        return baseAttribute;
    }

    public void setBaseAttribute(String baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

    public String getLinkedAttribute() {
        return linkedAttribute;
    }

    public void setLinkedAttribute(String linkedAttribute) {
        this.linkedAttribute = linkedAttribute;
    }

    public ArrayList<String> getAvailableSpecializations() {
        return availableSpecializations;
    }

    public void setAvailableSpecializations(ArrayList<String> availableSpecializations) {
        this.availableSpecializations = availableSpecializations;
    }

    public ArrayList<SpecializationContainer> getSelectedSpecializations() {
        return selectedSpecializations;
    }

    public void setSelectedSpecializations(ArrayList<SpecializationContainer> selectedSpecializations) {
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
        tableModel.addRow(addTableRow("Linked Attribute", linkedAttribute));
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

    public void addSpecialization(SpecializationContainer specializationContainer) {
        selectedSpecializations.add(specializationContainer);
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
