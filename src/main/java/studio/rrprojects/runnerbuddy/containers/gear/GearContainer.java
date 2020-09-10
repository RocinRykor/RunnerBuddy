package studio.rrprojects.runnerbuddy.containers.gear;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class GearContainer {
    String category;

    LinkedHashMap<String, Double> doubleList;
    LinkedHashMap<String, String> stringList;
    LinkedHashMap<String, Boolean> boolList;
    LinkedHashMap<String, Object> miscList;

    public GearContainer(String category, JSONObject object) {
        this.category = category;
         initTables();

        Map<String, Object> map = object.toMap();
        map.forEach((key, value) -> {
            if (value instanceof Double) {
                doubleList.put(key, (Double) value);
            } else if (value instanceof Integer) {
                doubleList.put(key, Double.valueOf((Integer) value));
            } else if (value instanceof String) {
                stringList.put(key, (String) value);
            } else if (value instanceof Boolean) {
                boolList.put(key, (Boolean) value);
            } else {
                miscList.put(key, value);
            }
        });

    }

    private void initTables() {
        doubleList = new LinkedHashMap<>();
        stringList = new LinkedHashMap<>();
        boolList = new LinkedHashMap<>();
        miscList = new LinkedHashMap<>();
    }
}
