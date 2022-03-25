package studio.rrprojects.runnerbuddy.containers.items.weapons;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.util_library.JSONUtil;

public class FirearmWeaponItem extends Buyable {
        int ammoCount;
        String ammoType;
        String firingModes;
        String damageCode;
        String recoilCompensation;

    public FirearmWeaponItem(String name) {
        super(name);
    }

    public FirearmWeaponItem() {
        super();
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
    public FirearmWeaponItem createNewFromJson(String name, JSONObject jsonObject) {
        FirearmWeaponItem firearmWeaponItem = new FirearmWeaponItem(name);
        firearmWeaponItem.ProcessJson(jsonObject);

        return firearmWeaponItem;
    }

    @Override
    public Buyable createNewFromJson() {
        return createNewFromJson(getName(), getJsonObject());
    }

    @Override
    public void PurchaseDialog() {
        super.PurchaseDialog();

        getPopup().setDescription(getDescription());
    }

    public String getDescription() {

        return super.getDescription() + "\n\n" +
                " === WEAPON STATS ===\n" +
                "Ammo: " +  ammoCount + "(" + ammoType + ")\n" +
                "Damage Code: " + damageCode + "\n" +
                "Recoil Compensation: " + recoilCompensation;
    }
}
