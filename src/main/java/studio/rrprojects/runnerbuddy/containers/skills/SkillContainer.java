package studio.rrprojects.runnerbuddy.containers.skills;

import com.eclipsesource.json.JsonObject;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class SkillContainer {
    private final String skillName;
    private final String attribute;
    private final boolean build_repair;
    private final String category;
    private final String defaults;
    private final String description;
    private final String source;
    private final ArrayList<String> specialization;
    private int baseLevel;
    private boolean isSpecialized;
    private String skillSpecialization;
    private int specializationLevel;
    private int skillLevel;
    private String skillResultString;
    private int totalCost;
    private String skillType;

    public String getSkillName() {
        return skillName;
    }

    public String getAttribute() {
        return attribute;
    }

    public boolean getBuild_repair() {
        return build_repair;
    }

    public String getCategory() {
        return category;
    }

    public String getDefaults() {
        return defaults;
    }

    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }

    public ArrayList<String> getSpecialization() {
        return specialization;
    }

    public SkillContainer(String skillName, String attribute, boolean build_repair, String category, String defaults, String description, String source, ArrayList<String> specialization) {
        this.skillName = skillName;
        this.attribute = attribute;
        this.build_repair = build_repair;
        this.category = category;
        this.defaults = defaults;
        this.description = description;
        this.source = source;
        this.specialization = specialization;
    }

    public SkillContainer(JsonObject.Member skill, String skillType) {
        skillName = TextUtils.TitleCase(skill.getName());
        attribute = TextUtils.TitleCase(skill.getValue().asObject().getString("attribute", "Intelligence"));
        build_repair = skill.getValue().asObject().getBoolean("build_repair", false);
        category = TextUtils.TitleCase(skill.getValue().asObject().getString("category", "UNKNOWN"));
        defaults = skill.getValue().asObject().getString("defaults", "UNKNOWN");
        description = skill.getValue().asObject().getString("description", "UNKNOWN");
        source = TextUtils.TitleCase(skill.getValue().asObject().getString("source", "UNKNOWN"));
        this.skillType = skillType;

        String specString = skill.getValue().asObject().getString("specialization", "UNKNOWN");
        specialization = new ArrayList<>();
        if (specString.length() > 0) {
            specialization.addAll(Arrays.asList(specString.split(",")));
        } else {
            specialization.add("CUSTOM");
        }

    }

    public String getBuild_repairAsString() {
        if (build_repair) {
            return "Available";
        } else {
            return "Unavailable";
        }
    }

    public void setBaseLevel(int level) {
        baseLevel = level;
    }

    public void setIsSpecialized(boolean bool) {
        isSpecialized = bool;
    }

    public void setSpecialization(String skillName) {
        if (skillName.equalsIgnoreCase("None")) {
            skillSpecialization = null;
            skillLevel = baseLevel;
            specializationLevel = 0;
        } else {
            skillSpecialization = skillName;
            skillLevel = baseLevel - 1;
            specializationLevel = baseLevel + 1;
        }
    }

    public void setSkillResultString(String text) {
        skillResultString = text;
    }

    public String getSkillResultString() {
        return skillResultString;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getSkillType() {
        return skillType;
    }

    public boolean isBuild_repair() {
        return build_repair;
    }

    public int getBaseLevel() {
        return baseLevel;
    }

    public boolean isSpecialized() {
        return isSpecialized;
    }

    public String getSkillSpecialization() {
        return skillSpecialization;
    }

    public int getSpecializationLevel() {
        return specializationLevel;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }
}
