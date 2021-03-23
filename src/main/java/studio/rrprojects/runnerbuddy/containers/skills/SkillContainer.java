package studio.rrprojects.runnerbuddy.containers.skills;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.utils.TextUtils;
import studio.rrprojects.util_library.JSONUtil;

import java.util.ArrayList;

public class SkillContainer {
    private String skillName;
    private String attribute;
    private boolean build_repair;
    private String category;
    private String defaults;
    private String description;
    private String source;
    private ArrayList<String> specialization;
    private int baseLevel;
    private boolean isSpecialized;
    private String skillSpecialization;
    private int specializationLevel;
    private int skillLevel;
    private String skillResultString;
    private int totalCost;
    private String skillType;

    public SkillContainer(String skillName, String skillType, JSONObject jsonObject) {
        this.skillName = skillName;
        this.skillType = skillType;
        attribute = TextUtils.titleCase(JSONUtil.getString(jsonObject, "attribute", "Intelligence"));
        build_repair = JSONUtil.getBool(jsonObject, "build_repair", false);
        category = TextUtils.titleCase(JSONUtil.getString(jsonObject, "category", "Unknown"));
        defaults = JSONUtil.getString(jsonObject, "defaults", "Unknown");
        description = JSONUtil.getString(jsonObject, "description", "Unknown");
        source = TextUtils.titleCase(JSONUtil.getString(jsonObject, "source", "Unknown"));

        String specString = JSONUtil.getString(jsonObject, "specialization", "Unknown");
        specialization = new ArrayList<>();
        if (specString.length() > 0) {
            String[] splitString = specString.split(",");
            for (String spec: splitString) {
                if (spec.startsWith(" ")) {
                    specialization.add(spec.replaceFirst(" ",""));
                } else {
                    specialization.add(spec);
                }
            }
        } else {
            specialization.add("CUSTOM");
        }
    }

    private void ProcessSkill(JSONObject skill) {

        /*
        skillName = TextUtils.titleCase(skill.getName());
        attribute = TextUtils.titleCase(skill.getValue().asObject().getString("attribute", "Intelligence"));
        build_repair = skill.getValue().asObject().getBoolean("build_repair", false);
        category = TextUtils.titleCase(skill.getValue().asObject().getString("category", "UNKNOWN"));
        defaults = skill.getValue().asObject().getString("defaults", "UNKNOWN");
        description = skill.getValue().asObject().getString("description", "UNKNOWN");
        source = TextUtils.titleCase(skill.getValue().asObject().getString("source", "UNKNOWN"));
        this.skill = skill;
        this.skillType = skillType;

        String specString = skill.getValue().asObject().getString("specialization", "UNKNOWN");
        specialization = new ArrayList<>();
        if (specString.length() > 0) {
            String[] splitString = specString.split(",");
            for (String spec: splitString) {
                if (spec.startsWith(" ")) {
                    specialization.add(spec.replaceFirst(" ",""));
                } else {
                    specialization.add(spec);
                }
            }
        } else {
            specialization.add("CUSTOM");
        }

         */
    }

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
