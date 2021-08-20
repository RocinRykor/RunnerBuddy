package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.constants.JsonFileConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.items.GearGroups.GearGroup;
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
        GearGroup weaponGroup = new GearGroup("Weapons");
        //weaponGroup.addWeapon("Personal Weapons", JsonFileConstants.GEAR_PERSONAL);
        weaponGroup.addWeapon("Firearms", JsonFileConstants.GEAR_FIREARMS);
        //weaponGroup.addWeapon("Heavy Weapons", JsonFileConstants.GEAR_HEAVY_WEAPONS);
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
}
