package studio.rrprojects.runnerbuddy.gui.cards;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.RunnerBuilderController;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Card {

    protected RunnerBuilderController controller;
    protected CharacterContainer characterContainer;

    public void Initialize() { }
    public abstract String getTitle();
    public abstract JPanel getPanel();
}
