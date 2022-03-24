package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.gui.popups.gear.AddBuyablePopup;
import studio.rrprojects.runnerbuddy.utils.TextUtils;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class Buyable {
    String name;
    String type = "Misc.";

    int cost = 0;
    String costRaw;

    int availabilityRating;
    int availabilityInterval;
    String availabilityTimeFrame;

    String availability;
    String legal;
    boolean variableRating;

    double streetIndex;

    int conceal;

    double weight;

    private AddBuyablePopup popup;
    private JSONObject jsonObject;

    public Buyable(String name) {
        this.name = name;
    }

    public Buyable(Buyable buyable) {
        name = buyable.getName();
    }

    public Buyable() {}

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

    public Buyable ProcessJson(JSONObject object) {
        this.jsonObject = object;
        setType(JSONUtil.getString(object, "type", "Misc"));

        //Cost
        costRaw = object.get("cost").toString();
        try {
            setCost(Integer.parseInt(costRaw));
        } catch (NumberFormatException e) {
            // TODO: 8/6/21 Process cost from a variable cost item
            setCost(-1);
            //e.printStackTrace();
        }

        //Availability
        availabilityRating = JSONUtil.getInt(object, "availability_rating", 1);
        availabilityInterval = JSONUtil.getInt(object, "availability_interval", 1);
        availabilityTimeFrame = JSONUtil.getString(object, "availability_time_frame", "hrs");

        if (availabilityRating == 1 || availabilityInterval == 1) {
            availability = "Always";
        } else {
            availability = availabilityRating + "/" + availabilityInterval + " " + availabilityTimeFrame;
        }

        //Street Index
        streetIndex = JSONUtil.getDouble(object, "street_index", 1);

        //Legal
        legal = JSONUtil.getString(object, "legal", "Legal");

        //VariableRating
        variableRating = JSONUtil.getBool(object, "variable_rating", false);

        //Conceal
        conceal = JSONUtil.getInt(object, "conceal",  0);

        //Weight
        weight = JSONUtil.getDouble(object, "weight", 0);

        return null;
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

    public String getDescription() {
        String output = "";
        output += ("Name: " + name) + "\n";
        output += ("Type: " + type) + "\n";
        output += ("Weight: " + weight) + "\n";
        output += ("Cost: " + cost) + "\n";
        output += ("Availability: " + availability) + "\n";
        output += ("Street Index: " + streetIndex) + "\n";
        output += ("Conceal Rating: " + conceal) + "\n";
        output += ("Legal: " + legal);

        return output;
    }

    public Buyable createNewFromJson(String name, JSONObject jsonObject) {
        Buyable buyable = new Buyable(name);
        buyable.ProcessJson(jsonObject);

        return buyable;
    }

    public String getClassName() {
        return "Buyable";
    }
}
