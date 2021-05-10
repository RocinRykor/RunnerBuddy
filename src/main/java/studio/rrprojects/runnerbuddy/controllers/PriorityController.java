package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityOptions;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PriorityController {
    private final CharacterContainer characterContainer;
    private final HashMap<String, PriorityOptions> masterPriorityMap;
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
        priorityJson = JSONUtil.loadJsonFromFile(FileUtil.loadFileFromPath("JSON/Misc/SR3E_priority_table.json"));
    }


    public PriorityOptions getOptionsByLevel(String priorityKey) {
        return masterPriorityMap.get(priorityKey.toLowerCase(Locale.ROOT));
    }
}
