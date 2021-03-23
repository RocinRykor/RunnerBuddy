package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.tree.TreePath;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SkillsController {

    private PriorityGroup priorityGroup;

    //Files
    String fileNameActive = "SR3E_active_skills.json";
    String fileNameKnowledge = "SR3E_knowledge_skills.json";
    String fileNameLanguage = "SR3E_language_skills.json";
    private Map<String, Map<String, JSONObject> > mapSkillsMaster;
    private Map<String, JSONObject> mapActiveSkills;
    private Map<String, JSONObject> mapKnowledgeSkills;
    private Map<String, JSONObject> mapLanguageSkills;

    //Maps

    public SkillsController(CharacterContainer characterContainer) {
        GeneratePriorityGroup();
        LoadJSONS();
    }

    private void LoadJSONS() {
        mapSkillsMaster = new HashMap<>();

        mapActiveSkills = loadFileToMap(fileNameActive);
        mapSkillsMaster.put("Active Skills", mapActiveSkills);

        mapKnowledgeSkills = loadFileToMap(fileNameKnowledge);
        mapSkillsMaster.put("Knowledge Skills", mapKnowledgeSkills);

        mapLanguageSkills = loadFileToMap(fileNameLanguage);
        mapSkillsMaster.put("Language Skills", mapLanguageSkills);
    }

    private Map<String, JSONObject> loadFileToMap(String fileName) {
        String filePath = "JSON/Skills/" + fileName;
        return JSONUtil.JsonToMap(JSONUtil.loadJsonFromFile(FileUtil.loadFileFromPath(filePath)));
    }

    private void GeneratePriorityGroup() {
        priorityGroup = new PriorityGroup("Skills");
        priorityGroup.addOption(1, "A", "50 Points", 50);
        priorityGroup.addOption(2, "B", "40 Points", 40);
        priorityGroup.addOption(3, "C", "34 Points", 34);
        priorityGroup.addOption(4, "D", "30 Points", 30);
        priorityGroup.addOption(5, "E", "27 Points", 27);
    }


    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }

    public Map<String, JSONObject> getMapActiveSkills() {
        return mapActiveSkills;
    }

    public Map<String, JSONObject> getMapKnowledgeSkills() {
        return mapKnowledgeSkills;
    }

    public Map<String, JSONObject> getMapLanguageSkills() {
        return mapLanguageSkills;
    }

    public Map<String, Map<String, JSONObject>> getMapSkillsMaster() {
        return mapSkillsMaster;
    }

    public SkillContainer searchSkillFromPath(TreePath path) {
        String skillType = path.getPathComponent(1).toString();
        String skillName = path.getPathComponent(3).toString();

        if (mapSkillsMaster.containsKey(skillType)) {
            Map<String, JSONObject> map = mapSkillsMaster.get(skillType);
            if (map.containsKey(skillName.toLowerCase(Locale.ROOT))) {
                return new SkillContainer(skillName, skillType, map.get(skillName.toLowerCase(Locale.ROOT)));
            }
        }

        return null;
    }
}