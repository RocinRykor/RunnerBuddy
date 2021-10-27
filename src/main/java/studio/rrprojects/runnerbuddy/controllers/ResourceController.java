package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.constants.JsonFileConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.runnerbuddy.containers.items.GearGroups.GearGroup;
import studio.rrprojects.runnerbuddy.containers.priority.ResourcePriority;
import studio.rrprojects.util_library.DebugUtils;

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
        GearGroup weaponGroup = new GearGroup("Weapons");
        weaponGroup.addWeapon("Personal Weapons", JsonFileConstants.GEAR_PERSONAL);
        weaponGroup.addWeapon("Firearms", JsonFileConstants.GEAR_FIREARMS);
        weaponGroup.addWeapon("Heavy Weapons", JsonFileConstants.GEAR_HEAVY_WEAPONS);
        //weaponGroup.addWeapon("Grenades and Explosives", JsonFileConstants.GEAR_GRENADES);
        //weaponGroup.addWeapon("Impact Ranged Weapons", JsonFileConstants.GEAR_IMPACT);
        //weaponGroup.addWeapon("Rockets and Missiles", JsonFileConstants.GEAR_ROCKETS);

        //Armor
        //GearGroup armorGroup = new GearGroup("Clothing");
        //armorGroup.addClothing("Armor and Clothing", JsonFileConstants.GEAR_ARMOR);

        //Entertainment
        //GearGroup entertainmentGroup = new GearGroup("Entertainment");
        //entertainmentGroup.addClothing("Entertainment", JsonFileConstants.GEAR_ENTERTAINMENT);

        //Security
        //GearGroup securityGroup = new GearGroup("Security");
        //securityGroup.addClothing("Surveillance and Security", JsonFileConstants.GEAR_SECURITY);

        weaponGroup.ProcessSubcategoryMap();
        //armorGroup.ProcessSubcategoryMap();
        //entertainmentGroup.ProcessSubcategoryMap();
        //securityGroup.ProcessSubcategoryMap();



        masterMap.put(weaponGroup.getCategory(), weaponGroup);
        //masterMap.put(armorGroup.getCategory(), armorGroup);
        //masterMap.put(entertainmentGroup.getCategory(), entertainmentGroup);
        //masterMap.put(securityGroup.getCategory(), securityGroup);
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

    public void addItemToInventory(Buyable buyable) {
        //Buyable processedBuyable = new Buyable(buyable);

        inventoryList.add(buyable);

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
}
