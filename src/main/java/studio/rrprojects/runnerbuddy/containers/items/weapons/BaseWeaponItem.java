package studio.rrprojects.runnerbuddy.containers.items.weapons;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.items.Buyable;
import studio.rrprojects.util_library.JSONUtil;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.LinkedHashMap;
import java.util.Locale;

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
    public void setRating(int itemRating) {
        super.setRating(itemRating);

        calculateCost();
        calculateDamage();
    }

    private void calculateDamage() {
        String damageString = getJsonObject().getString("damage");

        if (!damageString.toLowerCase(Locale.ROOT).contains("rating")) {
            return;
        }

        String replacedString = damageString.replaceAll("RATING", String.valueOf(getRating()));

        String expressionString = replacedString.substring(0, replacedString.indexOf(")") + 1);

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        Object evalDamageString = null;
        try {
            evalDamageString = engine.eval(expressionString);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        damageCode = evalDamageString.toString() + replacedString.substring(replacedString.indexOf(")"));
    }

    private void calculateCost() {
        String costString = getJsonObject().getString("cost");

        if (!costString.toLowerCase(Locale.ROOT).contains("rating")) {
            return;
        }

        String replacedString = costString.replaceAll("X", "*").replaceAll("RATING", String.valueOf(getRating()));

        int calculatedCost = -1;
        try {
            calculatedCost = Integer.parseInt(replacedString);
        } catch (NumberFormatException e) {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            try {
                calculatedCost = (int) engine.eval(replacedString);
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }
        }

        setCost(calculatedCost);
    }

    @Override
    public void setCost(int cost) {
        super.setCost(cost);
    }

    @Override
    public void PurchaseDialog() {
        super.PurchaseDialog();

        getPopup().setDescription(getDescription());
    }

    public String getDescription() {
        return super.getDescription() + "\n\n" +
                " === WEAPON STATS ===\n" +
                "Damage Code: " + damageCode + "\n";
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
