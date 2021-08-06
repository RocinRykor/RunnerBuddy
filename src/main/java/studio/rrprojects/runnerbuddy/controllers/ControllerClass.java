package studio.rrprojects.runnerbuddy.controllers;

import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;
import studio.rrprojects.runnerbuddy.textbuilder.TextBuilder;

public class ControllerClass {

    private PriorityContainer selectedPriority;

    public void setSelectedPriority(PriorityContainer priorityContainer) {
        selectedPriority = priorityContainer;
        UpdateEvent();
    }

    public void UpdateEvent() {
    }

    public PriorityContainer getSelectedPriority() {
        return selectedPriority;
    }

    public String ValidCheck() {
        return null;
    }

    public void toJSON(JSONObject jsonObject) {
    }

    public void toTextObject(TextBuilder textBuilder) {
    }
}
