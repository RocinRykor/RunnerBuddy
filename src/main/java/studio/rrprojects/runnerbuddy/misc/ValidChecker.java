package studio.rrprojects.runnerbuddy.misc;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;

import java.util.ArrayList;

public class ValidChecker {
    private boolean softError;
    String validString = "Valid";
    String cautionString = "Caution";
    String errorString = "Error";

    private boolean isValid;
    private final ArrayList<String> hardErrorNotes;
    private final ArrayList<String> softErrorNotes;
    private CharacterContainer characterContainer;

    public ValidChecker() {
        isValid = true;
        softError = false;

        hardErrorNotes = new ArrayList<>();
        softErrorNotes = new ArrayList<>();
    }

    public void ProcessCharacter(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        //Check Race
        CheckValid(characterContainer.getRaceController().ValidCheck());
        //Check Info
        CheckValid(characterContainer.getDescriptionController().ValidCheck());
        //Check Attributes
        CheckValid(characterContainer.getAttributeController().ValidCheck());

        if (hardErrorNotes.size() > 0) {
            isValid = false;
        }

        if (softErrorNotes.size() > 0) {
            softError = true;
        }
    }

    private void CheckValid(String input) {
        if (!input.equalsIgnoreCase(validString)) {
            if (input.startsWith(cautionString)) {
                softErrorNotes.add(input);
            } else {
                hardErrorNotes.add(input);
            }
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public ArrayList<String> getHardErrorNotes() {
        return hardErrorNotes;
    }

    public ArrayList<String> getSoftErrorNotes() {
        return softErrorNotes;
    }

    public CharacterContainer getCharacterContainer() {
        return characterContainer;
    }

    public void setCharacterContainer(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
    }

    public boolean isSoftError() {
        return softError;
    }
}
