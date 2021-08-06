package studio.rrprojects.runnerbuddy.gui.cards;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;

import javax.swing.*;
import java.awt.*;

public class Card {
    private String title;
    private JPanel panel;
    private CharacterContainer characterContainer;

    public Card(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public CharacterContainer getCharacterContainer() {
        return characterContainer;
    }

    public void setCharacterContainer(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
    }

    public void Update() {
    }

    public JButton getButton() {
        return new JButton(title);
    }
}
