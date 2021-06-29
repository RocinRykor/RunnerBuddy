package studio.rrprojects.runnerbuddy.containers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import java.util.*;

public class SkillMap {
    private final String skillType;
    private final ArrayList<SkillContainer> listSkillsMaster;
    private final HashMap<String, ArrayList<SkillContainer>> mapSkillsByCategory;

    public SkillMap(JSONObject jsonObject, String skillType) {
        this.skillType = skillType;

        listSkillsMaster = new ArrayList<>();
        mapSkillsByCategory = new LinkedHashMap<>();

        for (String skillName: jsonObject.keySet()) {
            JSONObject skill = jsonObject.getJSONObject(skillName);
            SkillContainer skillContainer = new SkillContainer(skillName, skill);
            String category = skillContainer.getCategory();

            if (!mapSkillsByCategory.containsKey(category)) {
                mapSkillsByCategory.put(category, new ArrayList<>());
            }

            mapSkillsByCategory.get(category).add(skillContainer);

            listSkillsMaster.add(skillContainer);
        }

        SortEverything(); //Finish up by sorting the lists
    }

    private void SortEverything() {
        Comparator<SkillContainer> compareBySkillName = (SkillContainer s1, SkillContainer s2) ->
                s1.getSkillName().compareTo( s2.getSkillName());

        listSkillsMaster.sort(compareBySkillName);

        for (String skillCategory: mapSkillsByCategory.keySet()) {
            ArrayList<SkillContainer> categoryList = mapSkillsByCategory.get(skillCategory);
            categoryList.sort(compareBySkillName);
        }

    }

    public String getSkillType() {
        return skillType;
    }

    public ArrayList<SkillContainer> getListSkillsMaster() {
        return listSkillsMaster;
    }

    public HashMap<String, ArrayList<SkillContainer>> getMapSkillsByCategory() {
        return mapSkillsByCategory;
    }

}
