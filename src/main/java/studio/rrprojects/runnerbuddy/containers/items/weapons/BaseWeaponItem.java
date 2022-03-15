package studio.rrprojects.runnerbuddy.containers.items.weapons;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.util_library.DebugUtils;
import studio.rrprojects.util_library.JSONUtil;

import java.util.LinkedHashMap;

public class BaseWeaponItem extends Buyable {
    int ammoCount;
    String ammoType;
    String firingModes;
    String damageCode;
    String recoilCompensation;

    private LinkedHashMap<String, Object> jsonMap;

    public BaseWeaponItem(String name) {
        super(name);
    }

    public BaseWeaponItem() {
        super();
    }

    @Override
    public Buyable ProcessJson(JSONObject object) {
        super.ProcessJson(object);

        //TODO - Change to Base Weapon (Just a damage code)

        ammoCount = JSONUtil.getInt(object, "ammo",  1);
        ammoType = JSONUtil.getString(object, "ammo_type", "C");
        firingModes = JSONUtil.getString(object, "mode", "SS");
        damageCode = JSONUtil.getString(object, "damage", "-1M");
        recoilCompensation = JSONUtil.getString(object, "rc", "N/A");

        return null;
    }

    @Override
    public void PurchaseDialog() {
        super.PurchaseDialog();

        getPopup().setDescription(getDescription());
    }

    public String getDescription() {
        System.out.println("GETTING DESCRIPTION:");

        //System.out.println(description);
        String output =  super.getDescription() + "\n\n";

        output += ("Ammo: " + ammoCount + "(" + ammoType + ")") + "\n";
        output += ("Firing Modes: " + firingModes) + "\n";
        output += ("Damage Code: " + damageCode) + "\n";
        output += ("Recoil Compensation: " + recoilCompensation);

        return output;
    }
}
