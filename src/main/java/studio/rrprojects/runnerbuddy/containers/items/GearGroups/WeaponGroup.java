package studio.rrprojects.runnerbuddy.containers.items.GearGroups;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.runnerbuddy.containers.items.WeaponItem;

import java.util.ArrayList;
import java.util.Map;

public class WeaponGroup extends GearGroup {
    public WeaponGroup(String title) {
        super(title);
    }

    @Override
    public void addGeneric(String subcategory, String fileName) {
        Map<String, JSONObject> tmp = ProcessJSON(subcategory, fileName);

        ArrayList<Buyable> arrayList = getSubcategoryMap().get(subcategory);
        for (String key: tmp.keySet()) {

            JSONObject jsonObject = tmp.get(key);
            WeaponItem item = new WeaponItem(key);
            item.ProcessJson(jsonObject);

            arrayList.add(item);
        }
    }
}
