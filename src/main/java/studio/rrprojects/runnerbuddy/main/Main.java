package studio.rrprojects.runnerbuddy.main;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.popups.NewCharacterPriorityPopup;

public class Main {
    public static void main(String[] args) {

        //LaunchWindow window = new LaunchWindow();

        //Create a new CharacterContainer for use going forward.
        CharacterContainer characterContainer = new CharacterContainer();

        //Priority Selection Popup Window
        new NewCharacterPriorityPopup("Select Character Priority", characterContainer);

        //CharacterCreationWindow characterCreationWindow = new CharacterCreationWindow();

    }
}