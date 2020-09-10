package studio.rrprojects.runnerbuddy.containers.attributes;

public class AttributePriorityContainer {

    String priorityLevel;
    int attributePoints;

    public AttributePriorityContainer(String priorityLevel, int attributePoints) {
        this.priorityLevel = priorityLevel;
        this.attributePoints = attributePoints;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getAttributePoints() {
        return attributePoints;
    }

    public void setAttributePoints(int attributePoints) {
        this.attributePoints = attributePoints;
    }
}
