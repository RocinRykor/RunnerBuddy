package studio.rrprojects.runnerbuddy.controllers;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class GearController {
    LinkedHashMap<String, ArrayList<String>> categoryList;
    LinkedHashMap<String, LinkedHashMap<String, GearContainer>> subcategoryList;
    LinkedHashMap<String, String> fileList;

    public GearController() {
        categoryList = new LinkedHashMap<>();
        subcategoryList = new LinkedHashMap<>();
        LoadFilesToCategoryList();
    }

    private void LoadFilesToCategoryList() {
        /*
        Here is a list of each of the files that will be added to the gear list
        Just add an element to the map with the name of the category and the name of the file as the value
         */
        fileList = new LinkedHashMap<>();
        AddFile("Weapons","Personal Weapons", "SR3E_personal_weapons");
        AddFile("Weapons","Impact Projectile Weapons", "SR3E_impact_projectile_weapons");
        AddFile("Weapons","Firearms (Small & Medium)", "SR3E_firearms");
        AddFile("Weapons","Firearms (Heavy)", "SR3E_heavy_weapons");
        AddFile("Weapons","Grenades", "SR3E_grenades");
        AddFile("Ammunition","Ammunition", "SR3E_ammunition");
        AddFile("Ammunition","Rockets and Missiles", "SR3E_rockets_and_missiles");
        AddFile("Armor","Clothing and Armor", "SR3E_clothing_and_armor");
        AddFile("Misc","Entertainment", "SR3E_entertainment");
        AddFile("Misc","Surveillance and Security", "SR3E_surveillance_and_security");
        AddFile("Misc","Weapon Accessories", "SR3E_weapon_accessories");
    }

    private void AddFile(String category, String subcategory, String fileName) {
        System.out.println("GearController: Loading File " + fileName);
        LinkedHashMap<String, GearContainer> newMap = ProcessFile(subcategory, fileName);
        subcategoryList.put(subcategory, newMap);

        if (!categoryList.containsKey(category)) {
            categoryList.put(category, new ArrayList<>());
        }

        categoryList.get(category).add(subcategory);
    }

    private LinkedHashMap<String, GearContainer> ProcessFile(String subcategory, String fileName) {
        String filePath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "RunnerBuddy"
                + File.separator + "JSON"
                + File.separator + "Gear"
                + File.separator + fileName + ".json";

        JSONObject mainObj;
        try {
            FileReader reader = new FileReader(new File(filePath));
            JsonObject tmp = (JsonObject) Json.parse(reader);
            mainObj = new JSONObject(tmp.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        LinkedHashMap<String, GearContainer> tmpList = new LinkedHashMap<>();
        for (Iterator<String> it = mainObj.keys(); it.hasNext(); ) {
            String object = it.next();

            tmpList.put(object, new GearContainer(subcategory, mainObj.getJSONObject(object)));
        }

        return tmpList;
    }

    public LinkedHashMap<String, ArrayList<String>> getCategoryList() {
        return categoryList;
    }

    public LinkedHashMap<String, String> getFileList() {
        return fileList;
    }

    public LinkedHashMap<String, LinkedHashMap<String, GearContainer>> getSubcategoryList() {
        return subcategoryList;
    }
}
