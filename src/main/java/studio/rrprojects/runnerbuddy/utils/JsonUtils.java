package studio.rrprojects.runnerbuddy.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    public static boolean getBoolOrDefault(JSONObject jsonObject, String key, boolean defualtBool) {
        boolean b;

        try {
            b = jsonObject.getBoolean(key);
        } catch(JSONException e) {
            return defualtBool;
        }

        return b;
    }

    public static String getStringOrDefault(JSONObject jsonObject, String key, String defaultString) {
        String s;

        try {
            s = jsonObject.getString(key);
        } catch(JSONException e) {
            return defaultString;
        }

        return s;
    }

    public static int getIntOrDefault(JSONObject jsonObject, String key, int defaultInt) {
        int i;

        try {
            i = jsonObject.getInt(key);
        } catch(JSONException e) {
            return defaultInt;
        }

        return i;
    }
}
