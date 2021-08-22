package studio.rrprojects.runnerbuddy.main;

import studio.rrprojects.runnerbuddy.gui.LaunchWindow;

import javax.swing.*;

public class Main {
    public static void main(final String[] args) {

        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (Exception ex) {
            System.out.println(ex);
            System.err.println("Failed to initialize LaF");
        }

        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        new LaunchWindow();
    }
}