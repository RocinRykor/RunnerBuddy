package studio.rrprojects.runnerbuddy.containers.priority;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListPriority extends PriorityContainer {
    ArrayList<String> availableOptions = new ArrayList<>();

    final String unavailableString = "Unavailable";

    public ListPriority(String priorityKey, String categoryKey, Object priorityObject) {
        super(priorityKey, categoryKey, priorityObject);

        if (priorityObject != null) {
            JSONArray valueArray = (JSONArray) priorityObject;

            for (Object object: valueArray) {
                availableOptions.add(object.toString());
            }

        } else {
            availableOptions.add(unavailableString);
        }

    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder(availableOptions.get(0));

        if (availableOptions.size() > 1) {
            for (int i = 1; i < availableOptions.size(); i++) {
                outputString.append(" / ").append(availableOptions.get(i));
            }
        }

        return getPriorityCategory() + " (" + outputString + ")";
    }

    public ArrayList<String> getAvailableOptions() {
        return availableOptions;
    }
}
