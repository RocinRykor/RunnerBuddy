package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;
import studio.rrprojects.runnerbuddy.misc.PriorityOption;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PriorityController {
    private final CharacterContainer characterContainer;
    private final HashMap<Object, Object> masterPriorityMap;
    private JSONObject priorityJson;

    public PriorityController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        masterPriorityMap = new HashMap<>();

        LoadPriorityFile();
        PopulatePriorityMap();
    }

    private void PopulatePriorityMap() {
        priorityJson.keySet().forEach(priorityKey -> {

            CreatePriorityOptionArrayList(priorityKey, priorityJson.getJSONObject(priorityKey));

            masterPriorityMap.put(priorityKey, new ArrayList<>());

        });
    }

    private void CreatePriorityOptionArrayList(String priorityKey, JSONObject jsonObject) {
        ArrayList<Object> arrayList = new ArrayList<>();

        jsonObject.keySet().forEach(categoryKey -> {
            arrayList.add(new PriorityOption(priorityKey, categoryKey, jsonObject.get(categoryKey)));
        });

    }

    private void LoadPriorityFile() {
        priorityJson = JSONUtil.loadJsonFromFile(FileUtil.loadFileFromPath("JSON/Misc/SR3E_priority_table.json"));
    }


}
