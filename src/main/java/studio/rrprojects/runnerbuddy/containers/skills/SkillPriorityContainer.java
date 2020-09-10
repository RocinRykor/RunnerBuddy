package studio.rrprojects.runnerbuddy.containers.skills;

public class SkillPriorityContainer {
    String priorityLevel;
    int skillPoints;

    public SkillPriorityContainer(String priorityLevel, int skillPoints) {
        this.priorityLevel = priorityLevel;
        this.skillPoints = skillPoints;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }
}
