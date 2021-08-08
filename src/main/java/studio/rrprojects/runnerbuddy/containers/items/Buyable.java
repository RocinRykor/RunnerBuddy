package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONException;
import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.constants.MiscConstants;
import studio.rrprojects.runnerbuddy.utils.TextUtils;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class Buyable {
    String name;
    int cost = 0;
    String type = "Misc.";

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
        try {
            setCost(object.getInt("cost"));
        } catch (JSONException e) {
            // TODO: 8/6/21
            setCost(-1);
            //e.printStackTrace();
        }

        setType(JSONUtil.getString(object, "type", "Misc"));
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
}
