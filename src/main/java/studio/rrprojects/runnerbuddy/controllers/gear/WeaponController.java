package studio.rrprojects.runnerbuddy.controllers.gear;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;
import studio.rrprojects.runnerbuddy.containers.gear.MeleeWeapon;
import studio.rrprojects.runnerbuddy.containers.gear.RangedWeapon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class WeaponController{
    private final CharacterContainer characterContainer;
    ArrayList<GearContainer> weaponList;

    public WeaponController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        weaponList = new ArrayList<>();
        AddMeleeWeapon("Personal Weapons", "SR3E_personal_weapons");
        AddRangedWeapon("Impact Projectile Weapons", "SR3E_impact_projectile_weapons");
        AddRangedWeapon("Firearms (Small & Medium)", "SR3E_firearms");
        AddRangedWeapon("Firearms (Heavy)", "SR3E_heavy_weapons");
        AddRangedWeapon("Grenades", "SR3E_grenades");
    }

    private void AddRangedWeapon(String subcategory, String fileName) {
        JSONObject mainObj = LoadFile(fileName);

        for (Iterator<String> it = mainObj.keys(); it.hasNext(); ) {
            String objectName = it.next();
            weaponList.add(new RangedWeapon(subcategory, objectName, mainObj.getJSONObject(objectName)));
        }
    }

    private void AddMeleeWeapon(String subcategory, String fileName) {
        JSONObject mainObj = LoadFile(fileName);

        for (Iterator<String> it = mainObj.keys(); it.hasNext(); ) {
            String objectName = it.next();
            weaponList.add(new MeleeWeapon(subcategory, objectName, mainObj.getJSONObject(objectName)));
        }
    }

    private JSONObject LoadFile(String fileName) {
        String filePath = characterContainer.getFileController().getGearDirectory() + File.separator + fileName + ".json";
        JSONObject mainObj;
        try {
            FileReader reader = new FileReader(new File(filePath));
            JsonObject tmp = (JsonObject) Json.parse(reader);
            mainObj = new JSONObject(tmp.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return mainObj;
    }
}
