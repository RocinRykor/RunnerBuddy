package studio.rrprojects.runnerbuddy.containers.magic.dominions;

import org.json.JSONObject;

public class TotemContainer extends DominionContainer{
    public TotemContainer(String name) {
        super(name);
    }

    @Override
    public void processJson(JSONObject jsonObject) {
        super.processJson(jsonObject);
    }

    @Override
    public String toString() {
        return getName();
    }
}
