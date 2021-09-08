package studio.rrprojects.runnerbuddy.utils;

import studio.rrprojects.runnerbuddy.constants.AttributeConstants;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.containers.SkillMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MiscUtils {

    public static ArrayList<String> basicAttributes() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add(AttributeConstants.BODY);
        tmp.add(AttributeConstants.QUICKNESS);
        tmp.add(AttributeConstants.STRENGTH);
        tmp.add(AttributeConstants.CHARISMA);
        tmp.add(AttributeConstants.INTELLIGENCE);
        tmp.add(AttributeConstants.WILLPOWER);
        return tmp;
    }

    public static DefaultTreeModel convertMasterSkillMapToJTree(LinkedHashMap<String, SkillMap> masterMap, String treeTitle) {
        DefaultMutableTreeNode masterNode = new DefaultMutableTreeNode(treeTitle);


        for (Map.Entry<String, SkillMap> entry: masterMap.entrySet()) {
            DefaultMutableTreeNode baseNode = new DefaultMutableTreeNode(entry.getKey());

            SkillMap skillMap = entry.getValue();
            HashMap<String, ArrayList<SkillContainer>> categoryMap = skillMap.getMapSkillsByCategory();

            for (String skillCategory: categoryMap.keySet()) {
                ArrayList<SkillContainer> listSkills = categoryMap.get(skillCategory);
                DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(skillCategory);

                for (SkillContainer skill: listSkills) {

                    //System.out.println("MiscUtils: " + skill);

                    DefaultMutableTreeNode skillNode = new DefaultMutableTreeNode(skill);
                    categoryNode.add(skillNode);
                }


                baseNode.add(categoryNode);
            }

            masterNode.add(baseNode);

        }

        return new DefaultTreeModel(masterNode);
    }

}
