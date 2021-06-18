package studio.rrprojects.runnerbuddy.gui.cards;

import javax.swing.*;

public class Card {
    private String title;
    private JPanel panel;

    public Card() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void Update() {
    }
}
