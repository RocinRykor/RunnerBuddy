package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONException;
import org.json.JSONObject;

public class WeaponItem extends Buyable{

    public WeaponItem(String name) {
        super(name);
    }

    @Override
    public void ProcessJson(JSONObject object) {
        super.ProcessJson(object);

        try {
            setCost(object.getInt("cost"));
        } catch (JSONException e) {
            // TODO: 8/6/21
            setCost(-1);
            //e.printStackTrace();
        }

    }
}