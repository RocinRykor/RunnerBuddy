package studio.rrprojects.runnerbuddy.containers.priority;

import studio.rrprojects.runnerbuddy.constants.MiscConstants;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

public class ResourcePriority extends PriorityContainer {
    private final Integer pointValue;

    public ResourcePriority(String priorityKey, String categoryKey, Object priorityObject) {
        super(priorityKey, categoryKey, priorityObject);

        pointValue = Integer.valueOf(priorityObject.toString());
    }

    @Override
    public String toString() {
        String pointString = TextUtils.IntToCash(pointValue);

        return getPriorityCategory() + ": " + pointString;
    }
}
