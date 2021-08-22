package studio.rrprojects.runnerbuddy.containers.priority;

import studio.rrprojects.runnerbuddy.utils.TextUtils;

public class PriorityContainer {

    private final String priorityKey;
    private final String priorityCategory;
    private final Object valueRaw;

    public PriorityContainer(String priorityKey, String categoryKey, Object priorityObject) {
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
        return "Level " + priorityKey + ": " + toString();
    }
}
