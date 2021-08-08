package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.constants.JsonFileConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.items.GearGroups.GearGroup;
import studio.rrprojects.runnerbuddy.containers.items.GearGroups.WeaponGroup;
import studio.rrprojects.runnerbuddy.containers.priority.ResourcePriority;

import java.util.LinkedHashMap;

public class ResourceController extends ControllerClass {
    private final CharacterContainer characterContainer;
    int startingNuyen = 0;
    private LinkedHashMap <String, GearGroup> masterMap = new LinkedHashMap<>();

    public ResourceController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        LoadAllGear();
    }

    private void LoadAllGear() {
        //Weapons
        WeaponGroup weaponGroup = new WeaponGroup("Weapons");
        weaponGroup.addGeneric("Personal Weapons", JsonFileConstants.GEAR_PERSONAL);
        weaponGroup.addGeneric("Firearms", JsonFileConstants.GEAR_FIREARMS);
        weaponGroup.addGeneric("Heavy Weapons", JsonFileConstants.GEAR_HEAVY_WEAPONS);

        WeaponGroup armorGroup = new WeaponGroup("Armor");
        armorGroup.addGeneric("Armor and Clothing", JsonFileConstants.GEAR_ARMOR);

        weaponGroup.ProcessSubcategoryMap();
        armorGroup.ProcessSubcategoryMap();


        masterMap.put(weaponGroup.getCategory(), weaponGroup);
        masterMap.put(armorGroup.getCategory(), armorGroup);
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
}
