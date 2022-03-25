package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.constants.FileConstants;
import studio.rrprojects.runnerbuddy.constants.JsonFileConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.runnerbuddy.containers.items.GearGroups.GearGroup;
import studio.rrprojects.runnerbuddy.containers.priority.ResourcePriority;
import studio.rrprojects.util_library.DebugUtils;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ResourceController extends ControllerClass {
    private final CharacterContainer characterContainer;
    int startingNuyen = 0;
    private final LinkedHashMap <String, GearGroup> masterMap = new LinkedHashMap<>();
    private ArrayList<Buyable> inventoryList = new ArrayList<>();

    public ResourceController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        LoadAllGear();
    }

    private void LoadAllGear() {
        //Weapons
        ProcessGearFile(JsonFileConstants.GEAR_PERSONAL);
        ProcessGearFile(JsonFileConstants.GEAR_FIREARMS);

    }

    private void ProcessGearFile(String filePath) {
        //Process JSON
        JSONObject jsonObj =  FileUtil.getJsonFromResource(FileConstants.RESOURCE_GEAR + filePath);

        //Add to GearGroup
        JSONObject firstObject = JSONUtil.getFirstIndex(jsonObj);
        String base = firstObject.getString("base");

        if (!masterMap.containsKey(base)) {
            masterMap.put(base, new GearGroup());
        }

        masterMap.get(base).ProcessJSON(jsonObj);
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

    public LinkedHashMap<String, GearGroup> getMasterMap() {
        return masterMap;
    }

    public void addItemToInventory(Buyable buyable, int itemRating, int itemQuantity) {

        for (int i = 0; i < itemQuantity; i++) {
            Buyable processedBuyable = buyable.createNewFromJson();
            processedBuyable.setRating(itemRating);

            inventoryList.add(processedBuyable);
        }

        //TODO Refresh Gear Cards
    }

    public TreeModel InventoryToTree() {
        DebugUtils.ProgressNormalMsg("Converting Inventory List to Tree");
        DebugUtils.VaraibleMsg(inventoryList.toString());

        LinkedHashMap<String, ArrayList<Buyable>> tmpMap = new LinkedHashMap<>();
        for (Buyable item : inventoryList) {
            if (!tmpMap.containsKey(item.getType())) {
                tmpMap.put(item.getType(), new ArrayList<>());
            }
            tmpMap.get(item.getType()).add(item);
        }

        DefaultMutableTreeNode masterNode = new DefaultMutableTreeNode("Inventory");
        for (String key: tmpMap.keySet()) {
            DefaultMutableTreeNode typeNode = new DefaultMutableTreeNode(key);
            ArrayList<Buyable> itemList = tmpMap.get(key);
            for (Buyable buyable : itemList) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(buyable);
                typeNode.add(node);
            }

            masterNode.add(typeNode);
        }

        return new DefaultTreeModel(masterNode);
    }

    public int getCurrentNuyen() {
       int currentNuyen = getStartingNuyen();

        for (Buyable buyable : inventoryList) {
            currentNuyen -=  buyable.getCost();
        }

        return currentNuyen;
    }
}
