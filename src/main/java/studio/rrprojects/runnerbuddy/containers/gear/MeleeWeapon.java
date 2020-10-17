package studio.rrprojects.runnerbuddy.containers.gear;

import org.json.JSONObject;

public class MeleeWeapon extends GearContainer {

    public MeleeWeapon(String subcategory, String objectName, JSONObject jsonObject) {
        super("Weapons", subcategory, objectName, jsonObject);
    }
}
