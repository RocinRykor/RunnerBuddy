package studio.rrprojects.runnerbuddy.containers.priority;

import studio.rrprojects.runnerbuddy.utils.TextUtils;

public class PointPriority extends PriorityContainer {
    int pointValue = 0;

    public PointPriority(String priorityKey, String categoryKey, Object priorityObject) {
        super(priorityKey, categoryKey, priorityObject);

        pointValue = Integer.valueOf(priorityObject.toString());
    }

    @Override
    public String toString() {

        return getPriorityCategory() + ": " + pointValue + " Points";
    }
}
