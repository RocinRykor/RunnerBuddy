package studio.rrprojects.runnerbuddy.containers.skills;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.constants.AttributeConstants;
import studio.rrprojects.runnerbuddy.utils.JsonUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import java.util.ArrayList;
import java.util.Collections;

public class SkillContainer{

    private final String skillBaseName;
    private String skillType;
    private String skillName;
    private String baseAttribute;
    private String linkedAttribute;
    private boolean isBuildRepairAvailable;
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

        isBuildRepairAvailable = JsonUtils.getBoolOrDefault(skill, "build_repair", false);

        defaults = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "defaults", "None"));
        description = skill.getString("description");
        source = skill.getString("source");

        String specializationString = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "specialization", ""));
        availableSpecializations = processSpecializationString(specializationString);

        category = TextUtils.titleCase(skill.getString("category"));

        selectedSpecializations = new ArrayList<>();

        baseLevel = JsonUtils.getIntOrDefault(skill, "value", 1);
    }

    private ArrayList<String> processSpecializationString(String string) {
        ArrayList<String> tmp = new ArrayList<>();

        String[] splitString = string.split(", ");

        if (splitString[0].length() > 0) {
            Collections.addAll(tmp, splitString);
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

    public boolean isBuildRepairAvailable() {
        return isBuildRepairAvailable;
    }

    public void setBuildRepairAvailable(boolean buildRepairAvailable) {
        isBuildRepairAvailable = buildRepairAvailable;
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

    public void addSpecialization(SpecializationContainer specializationContainer) {
        selectedSpecializations.clear(); //Right now characters are limited to 1 spec, so this forces that
        selectedSpecializations.add(specializationContainer);
    }

    @Override
    public String toString() {
        return skillName;
    }

    public String getOverview() {
        return "Skill Name: " + skillName + "\n\n" +
                "Category: " + category +"\n\n" +
                "Linked Attribute: " + linkedAttribute +"\n\n" +
                "B/R: " + TextUtils.titleCase(String.valueOf(isBuildRepairAvailable)) + "\n\n" +
                "Defaults: " + defaults +"\n\n" +
                "Source: " + source + "\n\n" +
                "Description: " + description;
    }
}
