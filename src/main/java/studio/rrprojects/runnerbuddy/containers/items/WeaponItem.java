package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONObject;
import studio.rrprojects.util_library.DebugUtils;

import java.util.LinkedHashMap;

public class WeaponItem extends Buyable{

    private LinkedHashMap<String, Object> jsonMap;

    public WeaponItem(String name) {
        super(name);
    }

    @Override
    public void ProcessJson(JSONObject object) {
        super.ProcessJson(object);

        DebugUtils.VaraibleMsg("PROCESSING JSON: " + object);

        jsonMap = new LinkedHashMap<>();

        for (String key : object.keySet()) {
            //DebugUtils.UnknownMsg(key);
            jsonMap.put(key, object.get(key));
        }

    }

    @Override
    public void PurchaseDialog() {
        super.PurchaseDialog();

        getPopup().setDescription(getDescription());
    }

    public String getDescription() {
        System.out.println("GETTING DESCRIPTION:");

        //System.out.println(description);
        return super.getDescription() + "\n";
    }
}
