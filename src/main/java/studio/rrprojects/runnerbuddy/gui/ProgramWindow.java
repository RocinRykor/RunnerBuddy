package studio.rrprojects.runnerbuddy.gui;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.RunnerBuilderController;
import studio.rrprojects.runnerbuddy.gui.cards.*;
import studio.rrprojects.runnerbuddy.utils.JUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProgramWindow extends JFrame {

    private final RunnerBuilderController controller;
    private JPanel panelMain;
    private JPanel panelCards;

    CardLayout cardLayout;
    CharacterContainer characterContainer;

    //Card
    private JSplitPane splitPane1;
    private ArrayList<ButtonObject> listButtons;
    private JPanel panelMenu;
    private ArrayList<Card> listCards;

    public ProgramWindow(String title, RunnerBuilderController controller, CharacterContainer characterContainer) {
        super(title);
        this.characterContainer = characterContainer;
        this.controller = controller;

        BeginInit();

        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void BeginInit() {
        SetMainPanel();
        SetMenuPanel();
        SetCardsPanel();

        CreateListOfButtons();
        FormatButtons();

        CreateListOfCards();
        FormatCards();
    }

    private void FormatCards() {
        for (Card card: listCards) {
            panelCards.add(card.getPanel(), card.getTitle());
        }

        cardLayout = (CardLayout) panelCards.getLayout();
    }

    private void CreateListOfCards() {
        listCards = new ArrayList<>();
        listCards.add(new Default(controller));
        listCards.add(new Info(controller, characterContainer));
        listCards.add(new Priority(controller, characterContainer));
        listCards.add(new Attributes(controller, characterContainer));
        listCards.add(new Skills(controller, characterContainer));
    }

    private void FormatButtons() {
        for (ButtonObject button : listButtons) {
            JUtils.SetDefaultButtonColors(button.getJButton());
            JUtils.SetDefaultButtonFont(button.getJButton(), 24);
            panelMenu.add(button.getJButton());
        }
    }

    private void CreateListOfButtons() {
        listButtons = new ArrayList<>();
        listButtons.add(new ButtonObject("Info"));
        listButtons.add(new ButtonObject("Priority"));
        listButtons.add(new ButtonObject("Attributes"));
        listButtons.add(new ButtonObject("Skills"));
        listButtons.add(new ButtonObject("Gear"));
        listButtons.add(new ButtonObject("Magic"));
        listButtons.add(new ButtonObject("Contacts"));
    }

    private void SetCardsPanel() {
        //Card Panel
        panelCards = new JPanel();
        panelCards.setLayout(new CardLayout(0, 0));
        panelCards.setPreferredSize(new Dimension(800, 700));
        JUtils.SetDefaultPanelColors(panelCards);
        splitPane1.setRightComponent(panelCards);
    }

    private void SetMenuPanel() {
        //Menu Panels
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());
        JUtils.SetDefaultPanelColors(panelMenu);

        splitPane1.setLeftComponent(panelMenu);
    }

    private void SetMainPanel() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout(0, 0));

        //Split Panel
        splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(50);
        splitPane1.setDividerSize(0);
        splitPane1.setOrientation(0);
        panelMain.add(splitPane1, BorderLayout.CENTER);
    }

    private void SwitchTo(String card) {
        cardLayout.show(panelCards, card);
        repaint();
    }

    private class ButtonObject {
        JButton jButton;

        public JButton getJButton() {
            return jButton;
        }

        public ButtonObject(String title) {
            jButton = new JButton(title);
            jButton.addActionListener(actionEvent -> SwitchTo("Card" + title)); }
        }
}
