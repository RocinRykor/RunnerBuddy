package studio.rrprojects.runnerbuddy.containers.items.GearGroups;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.constants.FileConstants;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.runnerbuddy.containers.items.ClothingItem;
import studio.rrprojects.runnerbuddy.containers.items.WeaponItem;
import studio.rrprojects.util_library.ConsoleColors;
import studio.rrprojects.util_library.DebugUtils;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class GearGroup {
    private String category;
    private ArrayList<Object> masterList = new ArrayList<>();
    private LinkedHashMap<String, ArrayList<Buyable>> subcategoryMap = new LinkedHashMap<>();

    public GearGroup(String category) {
        this.category = category;
    }

    public Map<String, JSONObject> ProcessJSON(String subcategory, String fileName) {
        DebugUtils.newDebugOut(ConsoleColors.RED, "PROCESSING: " + fileName);
        /*
        String filePath = FileConstants.RESOURCE_GEAR + fileName;
        InputStream is = GearGroup.class.getResourceAsStream(filePath);

        if (is == null) {
            DebugUtils.ErrorMsg("ERROR InputStream is NULL");
            return null;
        }
        
        JSONTokener token = new JSONTokener(is);
        JSONObject mainJson = new JSONObject(token);
         */

        JSONObject mainJson = FileUtil.getJsonFromResource(FileConstants.RESOURCE_GEAR + fileName);

        Map<String, JSONObject> mainMap = JSONUtil.JsonToMap(mainJson);

        if (!subcategoryMap.containsKey(subcategory)) {
            subcategoryMap.put(subcategory, new ArrayList<>());
        }

        return mainMap;
    }

    public void ProcessSubcategoryMap() {
        for (String key : subcategoryMap.keySet()) {
            GearGroup gearGroup = new GearGroup(key);
            ArrayList<Buyable> arrayList = subcategoryMap.get(key);

            LinkedHashMap<String, ArrayList<Buyable>> typeMap = new LinkedHashMap<>();

            for (Buyable buyable: arrayList) {
                String type = buyable.getType();

                if (!typeMap.containsKey(type)) {
                    typeMap.put(type, new ArrayList<>());
                }

                typeMap.get(type).add(buyable);
            }

            for (String key2: typeMap.keySet()) {
                GearGroup typeGroup = new GearGroup(key2);

                ArrayList<Buyable> buyableList = typeMap.get(key2);
                typeGroup.ProcessArrayList(buyableList);

                gearGroup.add(typeGroup);
            }

            masterList.add(gearGroup);
        }
    }

    private void add(GearGroup gearGroup) {
        masterList.add(gearGroup);
    }

    private void ProcessArrayList(ArrayList<Buyable> arrayList) {
        masterList.addAll(arrayList);
    }

    public Map<String, JSONObject> addGeneric(String subcategory, String fileName) {
        return ProcessJSON(subcategory, fileName);
    }

    public void addWeapon(String subcategory, String fileName) {
        Map<String, JSONObject> tmp = addGeneric(subcategory, fileName);

        if (tmp == null) {
            return;
        }

        ArrayList<Buyable> arrayList = getSubcategoryMap().get(subcategory);

        for (String key: tmp.keySet()) {

            JSONObject jsonObject = tmp.get(key);
            WeaponItem item = new WeaponItem(key);
            item.ProcessJson(jsonObject);

            arrayList.add(item);
        }
    }

    public void addClothing(String subcategory, String fileName) {
        Map<String, JSONObject> tmp = addGeneric(subcategory, fileName);

        ArrayList<Buyable> arrayList = getSubcategoryMap().get(subcategory);
        for (String key: tmp.keySet()) {

            JSONObject jsonObject = tmp.get(key);
            ClothingItem item = new ClothingItem(key);
            item.ProcessJson(jsonObject);

            arrayList.add(item);
        }
    }

    public String getCategory() {
        return category;
    }

    public LinkedHashMap<String, ArrayList<Buyable>> getSubcategoryMap() {
        return subcategoryMap;
    }

    @Override
    public String toString() {
        String displayString = "";
        for (String key: getSubcategoryMap().keySet()) {
            displayString += "=== " + key + " ===\n";
            ArrayList<Buyable> buyables = getSubcategoryMap().get(key);
            for (Buyable buyable: buyables) {
                displayString += buyable + "\n";
            }

            displayString += "\n";
        }

        return displayString;
    }

    public DefaultMutableTreeNode toNode() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(category);

        for (Object obj : masterList) {
            if (obj instanceof GearGroup) {

                GearGroup gearGroup = (GearGroup) obj;

                node.add(gearGroup.toNode());
            } else if (obj instanceof Buyable) {

                Buyable buyable = (Buyable) obj;

                node.add(buyable.toNode());
            } else {
                DebugUtils.newDebugOut(ConsoleColors.RED, "toNode Error: " + obj + " Incorrect class type");
            }
        }

        DebugUtils.newDebugOut(ConsoleColors.GREEN, "LOADED NODE: " + node.toString());
        return node;
    }

    public ArrayList<Object> getMasterList() {
        return masterList;
    }
}
