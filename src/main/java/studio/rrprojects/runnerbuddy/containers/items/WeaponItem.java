package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONException;
import org.json.JSONObject;
import studio.rrprojects.util_library.JSONUtil;

public class WeaponItem extends Buyable{

    public WeaponItem(String name) {
        super(name);
    }

    @Override
    public void ProcessJson(JSONObject object) {
        super.ProcessJson(object);
    }
}
