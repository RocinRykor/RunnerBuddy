package studio.rrprojects.runnerbuddy.misc;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Need my own custom Arraylist for this
 */
public class PriorityOptions {
    private final String priorityKey;
    private final ArrayList<PriorityObject> listOptions;

    public PriorityOptions(String priorityKey, JSONObject jsonObject) {
        this.priorityKey = priorityKey;
        listOptions = new ArrayList<PriorityObject>();

        for (String categoryKey : jsonObject.keySet()) {
            listOptions.add(new PriorityObject(priorityKey, categoryKey, jsonObject.get(categoryKey)));
        }
    }


    public boolean containsCategory(String key) {

        for (PriorityObject priorityObject : listOptions) {
            if (priorityObject.getPriorityCategory().equalsIgnoreCase(key)) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return listOptions.size();
    }

    public PriorityObject get(String key) {

        for (PriorityObject priorityObject : listOptions) {
            if (priorityObject.getPriorityCategory().equalsIgnoreCase(key)) {
                return priorityObject;
            }
        }

        return null;
    }
}
