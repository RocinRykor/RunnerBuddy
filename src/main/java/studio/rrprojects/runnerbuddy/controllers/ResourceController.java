package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.constants.FileConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.items.WeaponItem;
import studio.rrprojects.runnerbuddy.containers.priority.ResourcePriority;
import studio.rrprojects.util_library.JSONUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import static studio.rrprojects.runnerbuddy.constants.JsonFileConstants.GEAR_PERSONAL;

public class ResourceController extends ControllerClass {
    int startingNuyen = 0;
    private ArrayList<WeaponItem> weaponList = new ArrayList<>();

    public ResourceController(CharacterContainer characterContainer) {
        LoadAllGear();
    }

    private void LoadAllGear() {
        String weaponPath = FileConstants.RESOURCE_GEAR + GEAR_PERSONAL;
        JSONObject weaponJson;

        InputStream is = getClass().getResourceAsStream(weaponPath);

        assert is != null;

        JSONTokener token = new JSONTokener(is);
        weaponJson = new JSONObject(token);

        Map<String, JSONObject> weaponMap = JSONUtil.JsonToMap(weaponJson);

        for (String key : weaponMap.keySet()) {
           JSONObject object = weaponMap.get(key);
           WeaponItem weapon = new WeaponItem(key);
           weapon.ProcessJson(object);
            weaponList.add(weapon);
        }
    }

    @Override
    public void UpdateEvent() {
        super.UpdateEvent();
        ResourcePriority priority = (ResourcePriority) getSelectedPriority();
        startingNuyen = priority.getPointValue();
    }

    public int getStartingNuyen() {
        return startingNuyen;
    }

    public ArrayList<WeaponItem> getWeaponList() {
        return weaponList;
    }
}
