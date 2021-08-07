package studio.rrprojects.runnerbuddy.containers.items;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.constants.MiscConstants;
import studio.rrprojects.util_library.MathUtil;

public class Buyable {
    String name;
    int cost = 0;

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
    }

    @Override
    public String toString() {
        return getName() + ", " + getCost() + MiscConstants.NUYEN;
    }
}
