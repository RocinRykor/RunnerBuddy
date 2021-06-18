package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;

import java.awt.*;

public class RunnerBuilderController {
    CharacterContainer character;
    private Color colorBackground;
    private Color colorForeground;

    public RunnerBuilderController(String title) {
        System.out.println("Creating New Character");

        //Create A Blank Character Container
        CharacterContainer character = new CharacterContainer();

        //Start the Program Window
        //ProgramWindow appWindow = new ProgramWindow(title, this, character);
    }
}
