package studio.rrprojects.runnerbuddy.containers.magic;

import org.json.JSONArray;
import org.json.JSONObject;
import studio.rrprojects.util_library.JSONUtil;

import java.util.ArrayList;

public class AspectContainer {
    private final String aspectName;
    private String tradition;
    private double basePoints;
    private ArrayList<String> listTags = new ArrayList<>();

    public AspectContainer(String aspectName) {
        this.aspectName = aspectName;
    }

    public void processJson(JSONObject jsonObject) {
        tradition = JSONUtil.getString(jsonObject, "tradtion", "UNKNOWN");
        basePoints = jsonObject.getDouble("base_points");
        JSONArray tmpArray = jsonObject.getJSONArray("tags");
        for (Object tag : tmpArray) {
            listTags.add(tag.toString());
        }
    }

    public String getAspectName() {
        return aspectName;
    }

    public String getTradition() {
        return tradition;
    }

    public double getBasePoints() {
        return basePoints;
    }

    public ArrayList<String> getListTags() {
        return listTags;
    }

    @Override
    public String toString() {
        return getAspectName();
    }

    public boolean containsTag(String searchTerm) {
        for (String tag : listTags) {
            if (tag.contains(searchTerm)) {
                return true;
            }
        }
        return false;
    }
}
