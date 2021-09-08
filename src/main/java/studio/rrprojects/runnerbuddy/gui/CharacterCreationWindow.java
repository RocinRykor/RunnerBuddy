package studio.rrprojects.runnerbuddy.gui;

import studio.rrprojects.runnerbuddy.constants.ProgramInfoConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.attributes.Attributes;
import studio.rrprojects.runnerbuddy.gui.cards.export.SaveCard;
import studio.rrprojects.runnerbuddy.gui.cards.gear.StreetGear;
import studio.rrprojects.runnerbuddy.gui.cards.information.Info;
import studio.rrprojects.runnerbuddy.gui.cards.magic.MagicCard;
import studio.rrprojects.runnerbuddy.gui.cards.skills.SkillsCard;

import javax.swing.*;
import java.awt.*;

public class CharacterCreationWindow extends JFrame {
    private JPanel panelMain;

    CharacterContainer characterContainer;

    private static final String title = ProgramInfoConstants.PROGRAM_NAME + " " + ProgramInfoConstants.CURRENT_VERSION;


    public CharacterCreationWindow(CharacterContainer characterContainer) {
        super(title);
        this.characterContainer = characterContainer;

        BeginInit();

        finalizeJFrame();
    }

    private void BeginInit() {
        panelMain = new JPanel();
        panelMain.setPreferredSize(new Dimension(800, 800));
        CardManager cardManager = new CardManager(panelMain);

        cardManager.setCharacterContainer(characterContainer);

        cardManager.addCard(new Info("Info"));
        cardManager.addCard(new Attributes("Attributes"));
        cardManager.addCard(new SkillsCard("Skills"));
        cardManager.addCard(new StreetGear("Street Gear"));
        cardManager.addCard(new MagicCard("Magic"));
        cardManager.addCard(new SaveCard("Save/Export"));
    }

    private void finalizeJFrame() {
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
