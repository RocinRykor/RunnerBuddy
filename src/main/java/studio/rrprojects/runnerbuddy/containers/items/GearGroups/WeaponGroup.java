package studio.rrprojects.runnerbuddy.containers.items.GearGroups;

import org.json.JSONObject;

import java.util.Map;

public class WeaponGroup extends GearGroup {
    public WeaponGroup(String title) {
        super(title);
    }

    @Override
    public Map<String, JSONObject> addGeneric(String subcategory, String fileName) {
        Map<String, JSONObject> tmp = ProcessJSON(subcategory, fileName);
        return tmp;
    }
}
