package studio.rrprojects.runnerbuddy.containers.items.weapons;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.util_library.JSONUtil;

import java.util.LinkedHashMap;

public class BaseWeaponItem extends Buyable {
    String damageCode;

    private LinkedHashMap<String, Object> jsonMap;

    public BaseWeaponItem(String name) {
        super(name);
    }

    public BaseWeaponItem() {
        super();
    }

    @Override
    public void ProcessJson(JSONObject object) {
        super.ProcessJson(object);

        //TODO - Change to Base Weapon (Just a damage code)
        damageCode = JSONUtil.getString(object, "damage", "-1M");
    }

    @Override
    public void PurchaseDialog() {
        super.PurchaseDialog();

        getPopup().setDescription(getDescription());
    }

    public String getDescription() {
        String output =  super.getDescription() + "\n\n" +
                " === WEAPON STATS ===\n" +
                "Damage Code: " + damageCode + "\n";

        return output;
    }

    @Override
    public String getClassName() {
        return "BaseWeaponItem";
    }

    @Override
    public Buyable createNewFromJson(String name, JSONObject jsonObject) {
        LinkedHashMap<String, Buyable> weaponClassMap = new LinkedHashMap<>();
        weaponClassMap.put("Firearms", new FirearmWeaponItem());

        String category = jsonObject.getString("category");


        Buyable baseWeaponItem= null;
        if(weaponClassMap.containsKey(category)) {
            baseWeaponItem = weaponClassMap.get(category).createNewFromJson(name, jsonObject);
        } else {
            baseWeaponItem = new BaseWeaponItem(name);
            baseWeaponItem.ProcessJson(jsonObject);
        }

        return baseWeaponItem;
    }

    @Override
    public Buyable createNewFromJson() {
        return createNewFromJson(getName(), getJsonObject());
    }
}
