package studio.rrprojects.runnerbuddy.containers.priority;

import studio.rrprojects.runnerbuddy.utils.TextUtils;

public class PriorityContainer implements Comparable<PriorityContainer>{

    private final String priorityKey;
    private final String priorityCategory;
    private final Object valueRaw;

    public PriorityContainer(String priorityKey, String categoryKey, Object priorityObject) {
        //This is just the base object is never used! Check PointPriority, ListPriority, and ResourcePriority
        //Main three, will never change
        this.priorityKey = priorityKey;
        this.priorityCategory = TextUtils.titleCase(categoryKey);
        this.valueRaw = priorityObject;
    }

    public String getPriorityKey() {
        return priorityKey;
    }

    public String getPriorityCategory() {
        return priorityCategory;
    }

    public Object getValueRaw() {
        return valueRaw;
    }

    @Override
    public String toString() {
        return "PriorityContainer{" +
                "priorityKey='" + priorityKey + '\'' +
                ", priorityCategory='" + priorityCategory + '\'' +
                ", valueRaw=" + valueRaw +
                '}';
    }

    public String toMapDisplay() {
        return "Level " + priorityKey + ": " + this;
    }

    @Override
    public int compareTo(PriorityContainer p) {
        return this.getPriorityKey().compareTo(p.getPriorityKey());
    }
}
