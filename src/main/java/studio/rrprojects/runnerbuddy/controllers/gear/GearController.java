package studio.rrprojects.runnerbuddy.controllers.gear;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;
import studio.rrprojects.runnerbuddy.containers.gear.RangedWeapon;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class GearController {

    private final CharacterContainer characterContainer;
    private ArrayList<GearContainer> masterList;
    private LinkedHashMap<String, ArrayList<String>> categoryList;
    private LinkedHashMap<String, ArrayList<GearContainer>> subcategoryList;
    private ArrayList<GearContainer> selectedGearList;


    public GearController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        selectedGearList = new ArrayList<>();
        masterList = new ArrayList<>();

        ProcessStreetGear();

        System.out.println("MasterList Size: " + masterList.size());

        ProcessMasterList();
    }

    private void ProcessStreetGear() {

        AddItem("Weapons", "Personal Weapons", "SR3E_personal_weapons");
        AddItem("Weapons", "Impact Projectile Weapons", "SR3E_impact_projectile_weapons");
        AddItem("Weapons", "Firearms (Small & Medium)", "SR3E_firearms");
        AddItem("Weapons", "Firearms (Heavy)", "SR3E_heavy_weapons");
        AddItem("Weapons", "Grenades", "SR3E_grenades");

    }

    private void AddItem(String category, String subcategory, String fileName) {
        JSONObject mainObj = LoadFile(fileName);

        for (Iterator<String> it = mainObj.keys(); it.hasNext(); ) {
            String objectName = it.next();
            masterList.add(new GearContainer(category, subcategory, objectName, mainObj.getJSONObject(objectName)));
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


    private void ProcessMasterList() {
        categoryList = new LinkedHashMap<>();
        subcategoryList = new LinkedHashMap<>();

        for (GearContainer gear : masterList) {
            if (!categoryList.containsKey(gear.getCategory())) {
                categoryList.put(gear.getCategory(), new ArrayList<>());
            }

            if (!categoryList.get(gear.getCategory()).contains(gear.getSubcategory())) {
                categoryList.get(gear.getCategory()).add(gear.getSubcategory());
            }

            if (!subcategoryList.containsKey(gear.getSubcategory())) {
                subcategoryList.put(gear.getSubcategory(), new ArrayList<>());
            }

            subcategoryList.get(gear.getSubcategory()).add(gear);

        }
    }

    public ArrayList<GearContainer> getMasterList() {
        return masterList;
    }

    public CharacterContainer getCharacterContainer() {
        return characterContainer;
    }

    public LinkedHashMap<String, ArrayList<String>> getCategoryList() {
        return categoryList;
    }

    public LinkedHashMap<String, ArrayList<GearContainer>> getSubcategoryList() {
        return subcategoryList;
    }

    public GearContainer SearchForGear(String searchTerm, String selectedSubcategory) {
        for (GearContainer gear: subcategoryList.get(selectedSubcategory)) {
            if (gear.getItemName().equalsIgnoreCase(searchTerm)) {
                return gear;
            }
        }
        return null;
    }

    public void AddNewGear(GearContainer gear) {
        selectedGearList.add(gear);
    }

    public ArrayList<GearContainer> getSelectedGearList() {
        return selectedGearList;
    }

    public void ClearGearList() {
        selectedGearList.clear();
    }

    public GearContainer getGear(String gearName) {
        for (GearContainer gear : masterList) {
            if (gear.getItemName().equalsIgnoreCase(gearName)) {
                return gear;
            }
        }
        return null;
    }

    public GearContainer getSelectedGearByName(String gearName) {
        for (GearContainer gear: selectedGearList) {
            if (gear.getItemName().equalsIgnoreCase(gearName)) {
                return gear;
            }
        }

        return null;
    }
}
