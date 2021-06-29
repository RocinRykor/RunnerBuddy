package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.containers.SkillMap;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.misc.SkillConstants;

import java.io.InputStream;
import java.util.LinkedHashMap;

public class SkillsController extends ControllerClass {
    private final CharacterContainer characterContainer;

    private String activeSkillResourceString = "/JSON/Skills/SR3E_active_skills.json";
    private String knowledgeSkillResourceString = "/JSON/Skills/SR3E_knowledge_skills.json";
    private String languageSkillResourceString = "/JSON/Skills/SR3E_language_skills.json";
    private JSONObject activeSkillJSON;
    private JSONObject knowledgeSkillJSON;
    private JSONObject languageSkillJSON;

    private LinkedHashMap<String, SkillMap> masterSkillMap;

    public SkillsController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        loadJSONFiles();
        createSkillMaps();
    }

    private void createSkillMaps() {
        masterSkillMap = new LinkedHashMap<>();

        masterSkillMap.put(SkillConstants.ACTIVE, new SkillMap(activeSkillJSON, SkillConstants.ACTIVE));
        masterSkillMap.put(SkillConstants.KNOWLEDGE, new SkillMap(knowledgeSkillJSON, SkillConstants.KNOWLEDGE));
        masterSkillMap.put(SkillConstants.LANGUAGE, new SkillMap(languageSkillJSON, SkillConstants.LANGUAGE));
    }

    private void loadJSONFiles() {
        activeSkillJSON = loadAsJSON(activeSkillResourceString);
        knowledgeSkillJSON = loadAsJSON(knowledgeSkillResourceString);
        languageSkillJSON = loadAsJSON(languageSkillResourceString);
    }

    private JSONObject loadAsJSON(String s) {
        InputStream is = getClass().getResourceAsStream(s);
        JSONTokener token = new JSONTokener(is);

        return new JSONObject(token);
    }

    public LinkedHashMap<String, SkillMap> getMasterSkillMap() {
        return masterSkillMap;
    }
}
