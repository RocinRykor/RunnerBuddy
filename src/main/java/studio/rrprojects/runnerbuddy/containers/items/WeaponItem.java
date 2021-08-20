package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;

import java.util.LinkedHashMap;

public class WeaponItem extends Buyable{

    private LinkedHashMap<String, Object> jsonMap;

    public WeaponItem(String name) {
        super(name);
    }

    @Override
    public void ProcessJson(JSONObject object) {
        super.ProcessJson(object);

        System.out.println("PROCESSING JSON: " + object);

        jsonMap = new LinkedHashMap<>();

        for (String key : object.keySet()) {
            System.out.println(key);
            jsonMap.put(key, object.get(key));
        }

    }

    @Override
    public void PurchaseDialog() {
        super.PurchaseDialog();

        getPopup().setDescription(getDescription());
    }

    private String getDescription() {
        System.out.println("GETTING DESCRIPTION:");
        String description = "";

        for (String key : jsonMap.keySet()) {
            System.out.println(key);
            description += key + ": " + jsonMap.get(key) + "\n";
        }

        System.out.println(description);
        return description;
    }
}
