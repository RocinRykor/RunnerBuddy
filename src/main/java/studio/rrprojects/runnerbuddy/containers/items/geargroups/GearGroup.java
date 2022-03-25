package studio.rrprojects.runnerbuddy.containers.items.geargroups;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.items.BaseArmorItem;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.runnerbuddy.containers.items.weapons.BaseWeaponItem;
import studio.rrprojects.util_library.DebugUtils;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GearGroup {
    /*
    Base = Broad Category -> i.e Weapons, Vehicles
    Category = Subgroup -> i.e. Melee Weapons, Firearms, Cars, Bikes
    Type = Specific  Sub-Category -> i.e. Edged Weapons, Heavy Pistols, Off-Road Bike, Sports Car

    Example - Populated information will look like
    Base -> Weapons
    Category Map  -> <Melee Personal Weapons, [Edged Weapons, Pole-arms, Blunt Weapons]>
    Arraylists in the Category Map will be a list of individual items,, i.e Edged Weapons ->  [Knife, Sword, Katana]
     */

    private LinkedHashMap <String, JSONObject> rawMap = new LinkedHashMap<>();
    private LinkedHashMap<String, Buyable> buyableClassMap = new LinkedHashMap<>();

    private LinkedHashMap<String, ArrayList<String>> categoryMap = new LinkedHashMap<>();
    private LinkedHashMap<String, ArrayList<Buyable>> typeMap = new LinkedHashMap<>();

    private String base = null;

    public GearGroup() {
        InitBuyableMap();
    }

    private void InitBuyableMap() {
        buyableClassMap.put("Weapons", new BaseWeaponItem());
        buyableClassMap.put("Clothing", new BaseArmorItem());
    }

    public void ProcessJSON(JSONObject jsonObj) {
        JSONObject firstObj = JSONUtil.getFirstIndex(jsonObj);

        base = firstObj.getString("base");
        String category = firstObj.getString("category");

        rawMap.put(category, jsonObj);
        categoryMap.put(category, new ArrayList<>());

        ArrayList<String> typeList = categoryMap.get(category);

        Buyable buyable = null;
        if (buyableClassMap.containsKey(base)) {
            System.out.println("buyableClassMap: Success" );
            buyable = buyableClassMap.get(base);
        } else  {
            System.out.println("buyableClassMap: Failure" );
            buyable = new Buyable();
        }

        for (String key : jsonObj.keySet()) {
            JSONObject tmpObj = jsonObj.getJSONObject(key);
            DebugUtils.UnknownMsg("PROCESSING: " + key);

            String type = tmpObj.getString("type");

            if (!typeList.contains(type)) {
                typeList.add(type);
                typeMap.put(type, new ArrayList<>());
            }

            ArrayList<Buyable> buyableList = typeMap.get(type);

            buyableList.add(buyable.createNewFromJson(key, tmpObj));
        }

    }

    public DefaultMutableTreeNode toNode() {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(base);

        for (String category : categoryMap.keySet()) {
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(category);

            ArrayList<String> typeList  = categoryMap.get(category);

            for (String type : typeList) {
                DefaultMutableTreeNode typeNode = new DefaultMutableTreeNode(type);
                ArrayList<Buyable> buyableList = typeMap.get(type);

                for (Buyable buyable : buyableList) {
                    typeNode.add(buyable.toNode());
                }

                categoryNode.add(typeNode);
            }

            node.add(categoryNode);
        }

        return node;
    }

}
