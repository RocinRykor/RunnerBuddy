package studio.rrprojects.runnerbuddy.misc;

import org.json.JSONArray;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import java.util.ArrayList;

public class PriorityObject {
    private final String priorityLevel;
    private final String priorityCategory;
    private final Object valueRaw;
    private int valueInt;
    private ArrayList<String> valueList;
    private String displayString;

    public PriorityObject(String priorityKey, String categoryKey, Object priorityObject) {
        //Main three, will never change
        priorityLevel = priorityKey;
        priorityCategory = TextUtils.titleCase(categoryKey);
        valueRaw = priorityObject;

        //Generate both int value and List(String) value even though we will only ever use one of them
        valueList = new ArrayList<>();

        ProcessRawValue();

        ProcessDisplayString();
    }

    private void ProcessDisplayString() {
        if (priorityCategory.equalsIgnoreCase("Race") || priorityCategory.equalsIgnoreCase("Magic")) {
            displayString = ListToDisplayName();
        } else if (priorityCategory.equalsIgnoreCase("Resources")) {
            displayString = TextUtils.IntToCash(valueInt);
        } else {
            displayString = valueInt + " Points";
        }
    }

    private String ListToDisplayName() {
        String tmp = valueList.toString().replaceAll(", ", "/"); //Changes separator to a slash
        return tmp.substring(1, tmp.length()-1); //Remove the brackets on the string and return
    }

    private void ProcessRawValue() {
        if (valueRaw instanceof Integer) {
            valueInt = (Integer) valueRaw;
        } else if (valueRaw instanceof JSONArray) {
            JSONArray tmpArray = (JSONArray) valueRaw;
            for (Object value : tmpArray) {
                valueList.add(value.toString());
            }
        }
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public String getPriorityCategory() {
        return priorityCategory;
    }

    public String getDisplayString() {
        return displayString;
    }

    public ArrayList<String> getValueList() {
        return valueList;
    }

    public int getValueInt() {
        return valueInt;
    }
}
