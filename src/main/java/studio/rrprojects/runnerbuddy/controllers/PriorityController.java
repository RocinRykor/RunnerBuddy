package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityOptions;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PriorityController {
    private final CharacterContainer characterContainer;
    private final HashMap<String, PriorityOptions> masterPriorityMap;
    private final String[] listCategory = {"Magic", "Resources", "Attributes", "Skills", "Race"};
    private JSONObject priorityJson;


    public PriorityController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        masterPriorityMap = new HashMap<>();

        LoadPriorityFile();
        PopulatePriorityMap();
    }


    private void PopulatePriorityMap() {
        priorityJson.keySet().forEach(priorityKey -> {

            PriorityOptions priorityOptions =  new PriorityOptions(priorityKey, priorityJson.getJSONObject(priorityKey));

            masterPriorityMap.put(priorityKey, priorityOptions);

        });

        //Debuging
        System.out.println(masterPriorityMap.size());
        for (Map.Entry<String, PriorityOptions> key: masterPriorityMap.entrySet()) {
            System.out.println(key.getKey() + ": " + key.getValue().size());
        }
    }

    private void LoadPriorityFile() {

        InputStream is = getClass().getResourceAsStream("/JSON/Misc/SR3E_priority_table.json");
        JSONTokener token = new JSONTokener(is);

        priorityJson = new JSONObject(token);
    }


    public PriorityOptions getOptionsByLevel(String priorityKey) {
        return masterPriorityMap.get(priorityKey.toLowerCase(Locale.ROOT));
    }

    public String[] getListCategory() {
        return listCategory;
    }
}
