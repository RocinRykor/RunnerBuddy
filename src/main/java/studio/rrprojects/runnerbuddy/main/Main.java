package studio.rrprojects.runnerbuddy.main;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.RunnerBuilderController;
import studio.rrprojects.runnerbuddy.gui.CharacterCreationWindow;
import studio.rrprojects.runnerbuddy.gui.LaunchWindow;
import studio.rrprojects.runnerbuddy.gui.popups.NewCharacterPriorityPopup;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        //LaunchWindow window = new LaunchWindow();

        //Create a new CharacterContainer for use going forward.
        CharacterContainer characterContainer = new CharacterContainer();

        //Priority Selection Popup Window
        NewCharacterPriorityPopup priorityPopup = new NewCharacterPriorityPopup("Select Character Priority", characterContainer);

        //CharacterCreationWindow characterCreationWindow = new CharacterCreationWindow();

    }
}