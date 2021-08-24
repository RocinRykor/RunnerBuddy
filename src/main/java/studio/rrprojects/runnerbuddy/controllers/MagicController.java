package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.constants.MagicConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.magic.AspectContainer;
import studio.rrprojects.runnerbuddy.containers.magic.dominions.ElementContainer;
import studio.rrprojects.runnerbuddy.containers.magic.magetype.MagicUserContainer;
import studio.rrprojects.runnerbuddy.containers.magic.magetype.SpellCasterContainer;
import studio.rrprojects.runnerbuddy.containers.magic.dominions.DominionContainer;
import studio.rrprojects.runnerbuddy.containers.magic.dominions.TotemContainer;
import studio.rrprojects.runnerbuddy.containers.priority.ListPriority;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;
import studio.rrprojects.runnerbuddy.utils.TextUtils;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import java.util.*;

public class MagicController extends ControllerClass{
    private final CharacterContainer characterContainer;
    private boolean isMagical = false;
    private ArrayList<String> magicalOptions;
    private ListPriority selectedPriority;
    private final LinkedHashMap<String, MagicUserContainer> magicUserTypeMap = new LinkedHashMap<>();
    private JSONObject jsonAspects;
    private final LinkedHashMap<String, AspectContainer> mapAspects = new LinkedHashMap<>();
    private final LinkedHashMap<String, ArrayList<DominionContainer>> mapDominions = new LinkedHashMap<>();
    private JSONObject jsonTotems;
    private JSONObject jsonElements;

    public MagicController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        ProcessAspectFile();
        ProcessDominionFiles();
        ProcessMagicTypes();

    }

    private void ProcessDominionFiles() {
        jsonTotems = FileUtil.getJsonFromResource(MagicConstants.FILE_TOTEMS);
        jsonElements = FileUtil.getJsonFromResource(MagicConstants.FILE_ELEMENTS);

        Map<String, JSONObject> tmpMap = JSONUtil.JsonToMap(jsonTotems);

        ArrayList<DominionContainer> listTotem = new ArrayList<>();
        for (Map.Entry<String, JSONObject> entry: tmpMap.entrySet()) {
            String totemName = TextUtils.titleCase(entry.getKey());
            JSONObject totemJson = entry.getValue();
            TotemContainer totemContainer = new TotemContainer(totemName);
            totemContainer.processJson(totemJson);
            listTotem.add(totemContainer);
        }



        Collections.sort(listTotem);
        mapDominions.put(MagicConstants.TOTEM, listTotem);


        Map<String, JSONObject> tmpEleMap = JSONUtil.JsonToMap(jsonElements);

        ArrayList<DominionContainer> listElements = new ArrayList<>();
        for (Map.Entry<String, JSONObject> entry: tmpEleMap.entrySet()) {
            String elementName = TextUtils.titleCase(entry.getKey());
            JSONObject elementJson = entry.getValue();
            ElementContainer elementContainer = new ElementContainer(elementName);
            elementContainer.processJson(elementJson);
            listElements.add(elementContainer);
        }

        Collections.sort(listElements);
        mapDominions.put(MagicConstants.ELEMENT, listElements);
    }

    private void ProcessAspectFile() {
        jsonAspects = FileUtil.getJsonFromResource(MagicConstants.FILE_ASPECTS);

        Map<String, JSONObject> tmpMap = JSONUtil.JsonToMap(jsonAspects);
        for (Map.Entry<String, JSONObject> entry: tmpMap.entrySet()) {
            String aspectName = entry.getKey();
            JSONObject aspectJson = entry.getValue();
            AspectContainer aspectContainer = new AspectContainer(aspectName);
            aspectContainer.processJson(aspectJson);
            mapAspects.put(aspectName, aspectContainer);
        }
    }

    private void ProcessMagicTypes() {
        //Right now, hard coding all this, might look into a JSON for it later.
        SpellCasterContainer fullMagician = new SpellCasterContainer(MagicConstants.FULL_MAGICIAN, 25);
        fullMagician.addHermeticOption(MagicConstants.FULL_MAGE);
        fullMagician.addShamanicOption(MagicConstants.FULL_SHAMAN);

        SpellCasterContainer aspectedMagician = new SpellCasterContainer(MagicConstants.ASPECTED_MAGICIAN, 25);
        aspectedMagician.addHermeticOption(MagicConstants.ELEMENTALIST);
        aspectedMagician.addHermeticOption(MagicConstants.MAGE_CONJURER);
        aspectedMagician.addHermeticOption(MagicConstants.MAGE_SORCERER);
        aspectedMagician.addShamanicOption(MagicConstants.SHAMINIST);
        aspectedMagician.addShamanicOption(MagicConstants.SHAMAN_CONJURER);
        aspectedMagician.addShamanicOption(MagicConstants.SHAMAN_SORCERER);

        magicUserTypeMap.put(MagicConstants.FULL_MAGICIAN, fullMagician);
        magicUserTypeMap.put(MagicConstants.ASPECTED_MAGICIAN, aspectedMagician);
    }


    public CharacterContainer getCharacterContainer() {
        return characterContainer;
    }

    public boolean isMagical() {
        return isMagical;
    }

    public void setMagical(boolean magical) {
        isMagical = magical;
    }

    @Override
    public void setSelectedPriority(PriorityContainer priorityContainer) {
        selectedPriority = (ListPriority) priorityContainer;
        magicalOptions = selectedPriority.getAvailableOptions();

        //Check for if magical
        if (!magicalOptions.contains("Mundane")) {
            setMagical(true);
        }
    }

    public ArrayList<String> getMagicalOptions() {
        return magicalOptions;
    }

    @Override
    public ListPriority getSelectedPriority() {
        return selectedPriority;
    }

    public LinkedHashMap<String, MagicUserContainer> getMagicUserTypeMap() {
        return magicUserTypeMap;
    }

    public LinkedHashMap<String, AspectContainer> getMapAspects() {
        return mapAspects;
    }

    public LinkedHashMap<String, ArrayList<DominionContainer>> getMapDominions() {
        return mapDominions;
    }
}
