package studio.rrprojects.runnerbuddy.containers.skills;

public class SpecializationContainer {
    private String name;
    private int level;

    public SpecializationContainer(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
