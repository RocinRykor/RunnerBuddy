package studio.rrprojects.runnerbuddy.containers.gear;

import org.json.JSONObject;

public class MeleeWeapon extends GearContainer {
    String itemName;
    String category = "Weapons";
    String subcategory;
    JSONObject jsonObject;

    int baseCost, availabilityRating;

    public MeleeWeapon(String subcategory, String objectName, JSONObject jsonObject) {
        itemName = objectName;
        this.subcategory = subcategory;
        this.jsonObject = jsonObject;
        System.out.println(itemName);
        ProcessItem();
    }

    private void ProcessItem() {
        if (jsonObject.get("cost") instanceof Integer) {
            baseCost = jsonObject.getInt("cost");
        } else {
            baseCost = 1;
        }

        if (jsonObject.get("availability_rating") instanceof Integer) {
            availabilityRating = jsonObject.getInt("availability_rating");
        } else {
            availabilityRating = 1;
        }
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getSubcategory() {
        return subcategory;
    }

    @Override
    public int getItemAvailabilityRating() {
        return availabilityRating;
    }

    @Override
    public int getTotalCost() {
        return baseCost;
    }
}
