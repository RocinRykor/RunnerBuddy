package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONObject;
import studio.rrprojects.util_library.JSONUtil;

import java.util.LinkedHashMap;

public class WeaponItem extends Buyable{
    int ammoCount;
    String ammoType;
    String firingModes;
    String damageCode;
    String recoilCompensation;

    private LinkedHashMap<String, Object> jsonMap;

    public WeaponItem(String name) {
        super(name);
    }

    @Override
    public void ProcessJson(JSONObject object) {
        super.ProcessJson(object);


        ammoCount = JSONUtil.getInt(object, "ammo",  1);
        ammoType = JSONUtil.getString(object, "ammo_type", "C");
        firingModes = JSONUtil.getString(object, "mode", "SS");
        damageCode = JSONUtil.getString(object, "damage", "-1M");
        recoilCompensation = JSONUtil.getString(object, "rc", "N/A");

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
