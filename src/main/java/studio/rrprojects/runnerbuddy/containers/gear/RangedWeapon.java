package studio.rrprojects.runnerbuddy.containers.gear;

import org.json.JSONObject;

public class RangedWeapon extends GearContainer {
    public RangedWeapon(String subcategory, String objectName, JSONObject jsonObject) {
        super("Weapons", subcategory, objectName, jsonObject);
    }

}
