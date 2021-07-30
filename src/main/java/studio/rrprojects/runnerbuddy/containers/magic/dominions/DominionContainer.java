package studio.rrprojects.runnerbuddy.containers.magic.dominions;

import org.json.JSONObject;

public class DominionContainer implements Comparable<DominionContainer> {
    // A Domionion in the context is going to refer to either a Shaman's chosen totem, idol, or loa, as well as a mage's prefered element
    private final String name;
    private String category;
    private String enviroment;
    private String description;

    public DominionContainer(String name) {
        this.name = name;
    }

    public void processJson(JSONObject jsonObject) {
        description = jsonObject.getString("description");
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(DominionContainer d) {
        return this.getName().compareTo(d.getName());
    }
}
