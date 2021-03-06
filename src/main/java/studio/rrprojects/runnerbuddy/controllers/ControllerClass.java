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

    public String ValidCheck() {
        //Return "Valid" if everything checks out
        //Return "Caution - [Note]" if it is valid but missing something (unimportant info or not using all available points)
        //Return "Error - [Note]" If there is a game breaking problem (too many points spent, incompatible cyberware combo, etc)

        //If it returns 'null' it means that particular controller didn't set an override method

        return null;
    }
}
