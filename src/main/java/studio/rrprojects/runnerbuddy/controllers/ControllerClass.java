package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.misc.PriorityObject;

public class ControllerClass {

    private PriorityObject selectedPriority;

    public void setSelectedPriority(PriorityObject priorityObject) {
        selectedPriority = priorityObject;
    }

    public PriorityObject getSelectedPriority() {
        return selectedPriority;
    }
}
