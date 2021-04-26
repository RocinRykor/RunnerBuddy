package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityOption;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PriorityController {
    private final CharacterContainer characterContainer;
    private final HashMap<String, ArrayList<PriorityOption>> masterPriorityMap;
    private JSONObject priorityJson;

    public PriorityController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        masterPriorityMap = new HashMap<>();

        LoadPriorityFile();
        PopulatePriorityMap();
    }

    private void PopulatePriorityMap() {
        priorityJson.keySet().forEach(priorityKey -> {

            ArrayList<PriorityOption> tmpList = CreatePriorityOptionArrayList(priorityKey, priorityJson.getJSONObject(priorityKey));

            masterPriorityMap.put(priorityKey, tmpList);

        });

        //Debuging
        System.out.println(masterPriorityMap.size());
        for (Map.Entry<String, ArrayList<PriorityOption>> key: masterPriorityMap.entrySet()) {
            System.out.println(key.getKey() + ": " + key.getValue().size());
        }
    }

    private ArrayList<PriorityOption> CreatePriorityOptionArrayList(String priorityKey, JSONObject jsonObject) {
        ArrayList<PriorityOption> arrayList = new ArrayList<>();

        for (String categoryKey : jsonObject.keySet()) {
            arrayList.add(new PriorityOption(priorityKey, categoryKey, jsonObject.get(categoryKey)));
        }

        return arrayList;
    }

    private void LoadPriorityFile() {
        priorityJson = JSONUtil.loadJsonFromFile(FileUtil.loadFileFromPath("JSON/Misc/SR3E_priority_table.json"));
    }


    public ArrayList<PriorityOption> getOptionsByLevel(String priorityKey) {
        return masterPriorityMap.get(priorityKey.toLowerCase(Locale.ROOT));
    }
}
