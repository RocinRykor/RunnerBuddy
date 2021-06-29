package studio.rrprojects.runnerbuddy.containers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.utils.JsonUtils;
import studio.rrprojects.runnerbuddy.utils.MiscUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import java.util.ArrayList;

public class SkillContainer{
    private ArrayList<SpecializationObject> specializations;
    private String skillName;
    private String attribute;
    private boolean isBuildRepairAvailible;
    private String defaults;
    private String description;
    private String source;
    private String specialization;
    private String category;
    private int skillLevel;

    public SkillContainer(String name, JSONObject skill) {
        skillName = TextUtils.titleCase(name);

        attribute = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "attribute", "Intelligence"));
        isBuildRepairAvailible = JsonUtils.getBoolOrDefault(skill, "build_repair", false);
        defaults = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "defaults", "None"));
        description = skill.getString("description");
        source = skill.getString("source");
        specialization = TextUtils.titleCase(JsonUtils.getStringOrDefault(skill, "specialization", "None"));
        category = TextUtils.titleCase(skill.getString("category"));

        specializations = new ArrayList<>();

        skillLevel = JsonUtils.getIntOrDefault(skill, "value", 0);

        System.out.println("Skill Loaded: " + skillName);
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public ArrayList<SpecializationObject> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(ArrayList<SpecializationObject> specializations) {
        this.specializations = specializations;
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
        return "SkillContainer{" +
                "specializations=" + specializations +
                ", skillName='" + skillName + '\'' +
                ", attribute='" + attribute + '\'' +
                ", isBuildRepairAvailible=" + isBuildRepairAvailible +
                ", defaults='" + defaults + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", specialization='" + specialization + '\'' +
                ", category='" + category + '\'' +
                ", skillLevel=" + skillLevel +
                '}';
    }
}
