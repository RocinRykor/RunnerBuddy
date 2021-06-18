package studio.rrprojects.runnerbuddy.containers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.utils.MiscUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;
import studio.rrprojects.util_library.JSONUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class RaceContainer {

    private String name = "";
    private Map<String, JSONObject> limitNatural;


    private JSONObject limitNaturalJSON;
    private JSONObject limitModifiedJSON;

    private JSONObject modifiersJSON;
    private JSONObject attributesJSON;
    private JSONObject biowareJSON;
    private LinkedHashMap<String, Integer> modifiersAttributes;
    private LinkedHashMap<String, String>  modifiersBioware;


    public void Process(String raceName, JSONObject raceJson) {
        name = raceName;

        limitNaturalJSON = raceJson.getJSONObject("natural_attribute_limit");

        limitModifiedJSON = raceJson.getJSONObject("racial_modified_limit");

        modifiersJSON = raceJson.getJSONObject("racial_modifiers");

        modifiersAttributes = new LinkedHashMap<>();

        try {
            attributesJSON = modifiersJSON.getJSONObject("attributes");
        } catch (Exception e) {
            //e.printStackTrace();
        }


        if (attributesJSON != null) {
            for (String key : attributesJSON.keySet()) {
                int modValue = attributesJSON.getInt(key);
                modifiersAttributes.put(TextUtils.titleCase(key), modValue);
            }
        }

        modifiersBioware = new LinkedHashMap<>();

        try {
            biowareJSON = modifiersJSON.getJSONObject("bioware");
        } catch (Exception e) {
            //e.printStackTrace();
        }

        if (biowareJSON != null) {
            for (String key : biowareJSON.keySet()) {
                String modValue = biowareJSON.getString(key);
                modifiersBioware.put(TextUtils.titleCase(key), TextUtils.titleCase(modValue));
            }
        }


        System.out.println(modifiersAttributes.size());

    }

    public String getName() {
        return name;
    }

    public LinkedHashMap<String, Integer> getModifiersAttributes() {
        return modifiersAttributes;
    }

    public LinkedHashMap<String, String> getModifiersBioware() {
        return modifiersBioware;
    }

    public String getDescription() {
        String title = "Name: " + TextUtils.titleCase(name) + "\n\n";

        String attributes = "Racial Attribute Modieifers:\n";
        for (String attribute :MiscUtils.basicAttributes()) {
            int modValue = 0;
            if (modifiersAttributes.containsKey(attribute)) {
                modValue = modifiersAttributes.get(attribute);
            }

            attributes += TextUtils.titleCase(attribute) + ": " + modValue + "\n";
        }

        return title + attributes;
    }
}
