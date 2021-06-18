package studio.rrprojects.runnerbuddy.gui;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.Attributes;
import studio.rrprojects.runnerbuddy.gui.cards.Card;
import studio.rrprojects.runnerbuddy.gui.cards.Info;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class CharacterCreationWindow extends JFrame {

    private JPanel panelMain;
    private JPanel panelCards;

    CardLayout cardLayout;
    CharacterContainer characterContainer;

    //Card
    private JSplitPane splitPane1;
    private ArrayList<ButtonObject> listButtons;
    private JPanel panelMenu;

    private static final String title = "RunnerBuddy v1";
    private LinkedHashMap<String, Card> cardsMap;

    public CharacterCreationWindow(CharacterContainer characterContainer) {
        super(title);
        this.characterContainer = characterContainer;

        BeginInit();

        finalizeJFrame();
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

    private void CreateListOfButtons() {
        listButtons = new ArrayList<>();
        listButtons.add(new ButtonObject("Info/Race"));
        listButtons.add(new ButtonObject("Attributes"));
    }

    private void FormatButtons() {
        for (ButtonObject button : listButtons) {
            panelMenu.add(button.getJButton());
        }
    }

    private void CreateListOfCards() {
        cardsMap = new LinkedHashMap<>();
        cardsMap.put("Info/Race", new Info(characterContainer));
        cardsMap.put("Attributes", new Attributes(characterContainer));
    }

    private void FormatCards() {
        for (Map.Entry<String, Card> entry: cardsMap.entrySet()) {
            String cardName = entry.getKey();
            Card card = entry.getValue();

            JPanel panel = card.getPanel();

            panelCards.add(panel, cardName);
        }

        cardLayout = (CardLayout) panelCards.getLayout();
    }

    private void SwitchTo(String cardName) {
        System.out.println("Switching To -> " + cardName);

        Card card = cardsMap.get(cardName);
        cardLayout.show(panelCards, cardName);

        card.Update();

        repaint();
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

    private void SetMenuPanel() {
        //Menu Panels
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());

        splitPane1.setLeftComponent(panelMenu);
    }

    private void SetCardsPanel() {
        //Card Panel
        panelCards = new JPanel();
        panelCards.setLayout(new CardLayout(0, 0));
        panelCards.setPreferredSize(new Dimension(800, 700));
        splitPane1.setRightComponent(panelCards);

    }

    private void finalizeJFrame() {
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private class ButtonObject {
        JButton jButton;

        public JButton getJButton() {
            return jButton;
        }

        public ButtonObject(String title) {
            jButton = new JButton(title);
            jButton.addActionListener(actionEvent -> {
                SwitchTo(jButton.getActionCommand());
            }); }
    }
}
