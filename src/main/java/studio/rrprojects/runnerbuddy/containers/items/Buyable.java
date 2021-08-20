package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONException;
import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.gui.popups.gear.AddBuyablePopup;
import studio.rrprojects.runnerbuddy.utils.TextUtils;
import studio.rrprojects.util_library.DebugUtils;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class Buyable {
    String name;
    String type = "Misc.";

    int cost = 0;
    String costRaw;

    int availibilityRating;
    int availabilityInterval;
    String availabilityTimeFrame;

    String availability;
    String legal;
    boolean variableRating;

    double streetIndex;

    private AddBuyablePopup popup;
    private JSONObject jsonObject;

    public Buyable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void ProcessJson(JSONObject object) {
        this.jsonObject = object;
        setType(JSONUtil.getString(object, "type", "Misc"));

        //Cost
        costRaw = object.get("cost").toString();
        try {
            setCost(Integer.parseInt(costRaw));
        } catch (JSONException e) {
            // TODO: 8/6/21
            setCost(-1);
            //e.printStackTrace();
        }

        //Availability
        availibilityRating = JSONUtil.getInt(object, "availability_rating", 1);
        availabilityInterval = JSONUtil.getInt(object, "availability_interval", 1);
        availabilityTimeFrame = JSONUtil.getString(object, "availability_time_frame", "hrs");

        if (availibilityRating == 1 || availabilityInterval == 1) {
            availability = "Always";
        } else {
            availability = availibilityRating + "/" + availabilityInterval + " " + availabilityTimeFrame;
        }

        //Street Index
        streetIndex = JSONUtil.getDouble(object, "street_index", 1);

        //Legal
        legal = JSONUtil.getString(object, "legal", "Legal");

        //VariableRating
        variableRating = JSONUtil.getBool(object, "variable_rating", false);



        DebugUtils.CautionMsg("Name: " + name);
        DebugUtils.CautionMsg("Type: " + type);
        DebugUtils.CautionMsg("Cost: " + cost);
        DebugUtils.CautionMsg("Availability: " + availability);
        DebugUtils.CautionMsg("Street Index: " + streetIndex);
        DebugUtils.CautionMsg("Legal: " + legal);

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getName() + ", " + TextUtils.IntToCash(getCost());
    }

    public MutableTreeNode toNode() {
        return new DefaultMutableTreeNode(this);
    }

    public void PurchaseDialog() {
        popup = new AddBuyablePopup(this);
    }

    public AddBuyablePopup getPopup() {
        return popup;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
