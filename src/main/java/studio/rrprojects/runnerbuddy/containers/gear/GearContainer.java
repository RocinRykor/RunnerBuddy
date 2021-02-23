package studio.rrprojects.runnerbuddy.containers.gear;

import org.json.JSONObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GearContainer {

    String itemName;
    String category;
    String subcategory;
    JSONObject jsonObject;

    int baseCost, availabilityRating;
    private int itemRating;

    public GearContainer(String category, String subcategory, String objectName, JSONObject jsonObject) {
        itemName = objectName;
        this.category = category;
        this.subcategory = subcategory;
        this.jsonObject = jsonObject;
        System.out.println(itemName);
        ProcessItem();
    }

    public GearContainer(GearContainer gearTemplate) {
        itemName = gearTemplate.itemName;
        this.category = gearTemplate.category;
        this.subcategory = gearTemplate.subcategory;
        this.jsonObject = gearTemplate.jsonObject;
        ProcessItem();
    }

    private void ProcessItem() {
        CalculateCost();

        if (jsonObject.get("availability_rating") instanceof Integer) {
            availabilityRating = jsonObject.getInt("availability_rating");
        } else {
            availabilityRating = 1;
        }
    }

    private void CalculateCost() {
        if (jsonObject.get("cost") instanceof Integer) {
            baseCost = jsonObject.getInt("cost");
        } else {
            String costString = jsonObject.getString("cost").replaceAll("RATING", String.valueOf(itemRating));
            costString = costString.replaceAll("X", "\\*");
            System.out.println(costString);

            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = null;
            try {
                result = engine.eval(costString);
            } catch (ScriptException e) {
                e.printStackTrace();
            }

            try {
                baseCost = Integer.parseInt(String.valueOf(result));
            } catch (NumberFormatException e) {
                baseCost = 1;
                System.out.println("ERROR: Cannot Calculate cost of item: " + itemName);
            }
        }
    }

    public int getItemRating() {
        return itemRating;
    }

    public void setItemRating(String ratingString) {
        itemRating = Integer.parseInt(ratingString);
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public int getTotalCost() {
        CalculateCost();
        return baseCost;
    }

    public int getAvailabilityRating() {
        return availabilityRating;
    }
}
