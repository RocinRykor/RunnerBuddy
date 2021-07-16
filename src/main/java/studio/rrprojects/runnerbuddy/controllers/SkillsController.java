package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import org.json.JSONTokener;
import studio.rrprojects.runnerbuddy.constants.SkillConstants;
import studio.rrprojects.runnerbuddy.containers.SkillMap;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SelectedSkillContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.io.InputStream;
import java.util.*;

public class SkillsController extends ControllerClass {
    private final CharacterContainer characterContainer;

    private String activeSkillResourceString = "/JSON/Skills/SR3E_active_skills.json";
    private String knowledgeSkillResourceString = "/JSON/Skills/SR3E_knowledge_skills.json";
    private String languageSkillResourceString = "/JSON/Skills/SR3E_language_skills.json";
    private JSONObject activeSkillJSON;
    private JSONObject knowledgeSkillJSON;
    private JSONObject languageSkillJSON;

    private LinkedHashMap<String, SkillMap> masterSkillMap;
    private ArrayList<SkillGroup> skillGroupList;
    private ArrayList<SelectedSkillContainer> selectedSkillList;

    public SkillsController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        
        createSkillLists();
        loadJSONFiles();
        createSkillMaps();
    }

    private void createSkillLists() {
        selectedSkillList = new ArrayList<>();
        
        skillGroupList = new ArrayList<>();

        skillGroupList.add(new SkillGroup(SkillConstants.ACTIVE, SkillConstants.ACTIVE_SUBCATEGORIES));
        skillGroupList.add(new SkillGroup(SkillConstants.KNOWLEDGE, SkillConstants.KNOWLEDGE_SUBCATEGORIES));
        skillGroupList.add(new SkillGroup(SkillConstants.LANGUAGE));

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

    public ArrayList<SelectedSkillContainer> getSelectedSkillList() {
        return selectedSkillList;
    }

    public boolean containsSkill(SkillContainer selectedSkill) {
        String skillName = selectedSkill.getSkillName();

        for (SelectedSkillContainer selectedSkillContainer : getSelectedSkillList()) {
            if (selectedSkillContainer.getSkillName().equalsIgnoreCase(skillName)) {
                return true;
            }
        }

        return false;
    }

    public DefaultTreeModel getSelectedSkillTree() {
        //Programmer's Note* - That could have gone worse... ultimtly being able to search nodes really helped

        DefaultMutableTreeNode masterNode = new DefaultMutableTreeNode("Selected Skills");

        //Create the hash map that we wiill use for organizing
        //I'm gonna have to get creative when searching through this

        LinkedHashMap<String, DefaultMutableTreeNode> skillTypeMap = new LinkedHashMap<>();

        skillTypeMap.put(SkillConstants.ACTIVE, new DefaultMutableTreeNode(SkillConstants.ACTIVE));
        skillTypeMap.put(SkillConstants.KNOWLEDGE, new DefaultMutableTreeNode(SkillConstants.KNOWLEDGE));
        skillTypeMap.put(SkillConstants.LANGUAGE, new DefaultMutableTreeNode(SkillConstants.LANGUAGE));

        for (SelectedSkillContainer skill: selectedSkillList) {
            String skillType = skill.getSkillType();
            String category = skill.getCategory();

            SkillGroup skillGroup = getSkillGroupBySkillType(skillType);

            //Lets get the easy one out of the way so I dont have to fuck with it - Language
            //Language doesnt have subcategories so I dont have to worry about them
            if (!skillGroup.hasCategories) {
                skillTypeMap.get(skillType).add(new DefaultMutableTreeNode(skill));
            } else {
                //This is where shit is gonna get fucky
                //Lets Start by grabbing the correct node
                DefaultMutableTreeNode baseNode = skillTypeMap.get(skillType);
                DefaultMutableTreeNode node = searchNodeForString(baseNode, category);

                //Hoping that search worked
                if (node == null) {
                    node = new DefaultMutableTreeNode(category);
                    baseNode.add(node);
                }

                node.add(new DefaultMutableTreeNode(skill));
            }

        }


        for (String key: skillTypeMap.keySet()) {
            masterNode.add(skillTypeMap.get(key));
        }

        return new DefaultTreeModel(masterNode);
    }

    private DefaultMutableTreeNode searchNodeForString(DefaultMutableTreeNode node, String string) {
        for (Iterator<TreeNode> it = node.children().asIterator(); it.hasNext(); ) {
            TreeNode n = it.next();
            if (n.toString().equalsIgnoreCase(string))
                return (DefaultMutableTreeNode) n;
        }
        return null;
    }

    public SkillGroup getSkillGroupBySkillType(String skillType) {
        for (SkillGroup skillGroup: skillGroupList) {
            if (skillGroup.skillType.equalsIgnoreCase(skillType)) {
                return skillGroup;
            }
        }

        return null;
    }

    public SkillGroup getSkillGroupByCategory(String category) {
        for (SkillGroup skillGroup: skillGroupList) {
            if (skillGroup.hasCategories) {
                for (String cat: skillGroup.categories) {
                    if (cat.equalsIgnoreCase(category)) {
                        return skillGroup;
                    }
                }
            }
        }

        return null;
    }
    
    private class SkillGroup {
        private final String skillType;
        private final String[] categories;
        private final boolean hasCategories;

        private SkillGroup(String skillType, String[] categories) {
            this.skillType = skillType;
            this.categories = categories;
            this.hasCategories = true;
        }

        private SkillGroup (String skillType) {
            this.skillType = skillType;
            this.categories = null;
            hasCategories = false;
        }

        public String getSkillType() {
            return skillType;
        }

        public String[] getCategories() {
            return categories;
        }

        public boolean isHasCategories() {
            return hasCategories;
        }

        @Override
        public String toString() {
            return "SkillGroup{" +
                    "skillType='" + skillType + '\'' +
                    ", categories=" + Arrays.toString(categories) +
                    ", hasCategories=" + hasCategories +
                    '}';
        }
    }
}
