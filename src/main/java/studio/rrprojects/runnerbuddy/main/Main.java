package studio.rrprojects.runnerbuddy.main;

import com.formdev.flatlaf.FlatDarculaLaf;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.LaunchWindow;
import studio.rrprojects.runnerbuddy.gui.popups.NewCharacterPriorityPopup;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
            UIManager.put( "Button.arc", 999 );
            //UIManager.put("Panel.background", Color.BLACK);
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