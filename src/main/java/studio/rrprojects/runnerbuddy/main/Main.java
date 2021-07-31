package studio.rrprojects.runnerbuddy.main;

import com.formdev.flatlaf.FlatDarculaLaf;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.LaunchWindow;
import studio.rrprojects.runnerbuddy.gui.popups.NewCharacterPriorityPopup;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        UIManager.LookAndFeelInfo[] listLAF = UIManager.getInstalledLookAndFeels();


        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        new LaunchWindow();

        //Create a new CharacterContainer for use going forward.
        //CharacterContainer characterContainer = new CharacterContainer();

        //Priority Selection Popup Window
        //new NewCharacterPriorityPopup(characterContainer);

        //CharacterCreationWindow characterCreationWindow = new CharacterCreationWindow();

    }
}