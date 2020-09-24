package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.ProgramWindow;
import studio.rrprojects.runnerbuddy.testing.TestProgramWindow;

public class RunnerBuilderController {
    CharacterContainer character;
    ProgramWindow appWindow;

    public RunnerBuilderController() {
        System.out.println("STARTING!");

        //Create A Blank Character Container
        CharacterContainer character = new CharacterContainer();

        //Start the Program Window
        ProgramWindow appWindow = new ProgramWindow("RunnerBuddy v1", this, character);
    }
}
