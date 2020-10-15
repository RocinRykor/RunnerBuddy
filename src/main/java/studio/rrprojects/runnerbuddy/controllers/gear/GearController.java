package studio.rrprojects.runnerbuddy.controllers.gear;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GearController {

    private final WeaponController weaponController;
    private final CharacterContainer characterContainer;
    private ArrayList<GearContainer> masterList;
    private LinkedHashMap<String, ArrayList<String>> categoryList;
    private LinkedHashMap<String, ArrayList<GearContainer>> subcategoryList;
    private LinkedHashMap<String, GearContainer> selectedGearList;


    public GearController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        selectedGearList = new LinkedHashMap<>();
        masterList = new ArrayList<>();
        weaponController = new WeaponController(characterContainer);

        masterList.addAll(weaponController.weaponList);
        System.out.println("MasterList Size: " + masterList.size());

        ProcessMasterList();
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

    public WeaponController getWeaponController() {
        return weaponController;
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
        selectedGearList.put(gear.getItemName(), gear);
    }

    public LinkedHashMap<String, GearContainer> getSelectedGearList() {
        return selectedGearList;
    }

    public void ClearGearList() {
        selectedGearList.clear();
    }
}
