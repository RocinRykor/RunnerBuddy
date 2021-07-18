package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.constants.JsonFileConstants;
import studio.rrprojects.runnerbuddy.containers.RaceContainer;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;

public class RaceController extends ControllerClass {

    private final CharacterContainer characterContainer;
    private JSONObject raceTableJson;
    private ArrayList<String> availibleRaces;
    private LinkedHashMap<String, RaceContainer> masterRaceMap;
    private RaceContainer selectedRace;

    public RaceController(CharacterContainer characterContainer){
        this.characterContainer = characterContainer;
        LoadPriorityFile();
        ConvertJsonToMap();
    }

    private void ConvertJsonToMap() {
        masterRaceMap = new LinkedHashMap<>();

        for (String raceName : raceTableJson.keySet()) {
            JSONObject raceJson = raceTableJson.getJSONObject(raceName);

            RaceContainer raceContainer = new RaceContainer();
            raceContainer.Process(raceName, raceJson);

            masterRaceMap.put(raceName, raceContainer);
        }
    }

    private void LoadPriorityFile() {

        InputStream is = getClass().getResourceAsStream("/JSON/Misc/" + JsonFileConstants.METATYPE_TABLE);

        assert is != null;
        JSONTokener token = new JSONTokener(is);

        raceTableJson = new JSONObject(token);
    }

    public void setAvailibleRaces(PriorityObject priorityObject) {
        availibleRaces = priorityObject.getValueList();
    }

    public ArrayList<String> getAvailibleRaces() {
        return availibleRaces;
    }

    public void setSelectedRace(String raceName) {
        selectedRace = masterRaceMap.get(raceName.toLowerCase(Locale.ROOT));
    }

    @Override
    public String ValidCheck() {
        if (getSelectedRace() != null) {
            return "Valid";
        } else {
            return "Error - No Race Chosen";
        }
    }

    public RaceContainer getSelectedRace() {
        return selectedRace;
    }
}
