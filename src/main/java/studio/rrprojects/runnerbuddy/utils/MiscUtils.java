package studio.rrprojects.runnerbuddy.utils;

import studio.rrprojects.runnerbuddy.containers.SkillContainer;
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
        tmp.add("Body");
        tmp.add("Quickness");
        tmp.add("Strength");
        tmp.add("Charisma");
        tmp.add("Intelligence");
        tmp.add("Willpower");
        return tmp;
    }

    public static ArrayList<String> allAttributes() {
        ArrayList<String> tmp = basicAttributes();
        tmp.add("Reaction");
        tmp.add("Essence");
        tmp.add("Magic");

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

                    System.out.println(skill);

                    DefaultMutableTreeNode skillNode = new DefaultMutableTreeNode(skill);
                    categoryNode.add(skillNode);
                }


                baseNode.add(categoryNode);
            }

            masterNode.add(baseNode);

        }
        /*
        for (int i = 0; i < 10; i++) {
            DefaultMutableTreeNode baseNode = new DefaultMutableTreeNode(i);
            for (int j = 0; j < 5; j++) {
                DefaultMutableTreeNode finalNode = new DefaultMutableTreeNode(j);
                baseNode.add(finalNode);
            }

            masterNode.add(baseNode);
        }

         */

        return new DefaultTreeModel(masterNode);
    }

}
