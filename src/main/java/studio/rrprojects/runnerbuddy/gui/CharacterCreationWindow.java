package studio.rrprojects.runnerbuddy.gui;

import studio.rrprojects.runnerbuddy.constants.AttributeConstants;
import studio.rrprojects.runnerbuddy.constants.ProgramInfoConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.*;

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

    private static final String title = ProgramInfoConstants.PROGRAM_NAME + " " + ProgramInfoConstants.CURRENT_VERSION;
    private LinkedHashMap<String, Card> cardsMap;
    private Card selectedCard;
    private ButtonObject buttonMagic;

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

        InitialCardPass();
        
        MagicCardCheck();
    }

    private void MagicCardCheck() {
        ArrayList<String> magicList = characterContainer.getMagicController().getMagicalOptions();
        if (!magicList.contains("Mundane")) {
            buttonMagic.getJButton().setVisible(true);
        } else {
            buttonMagic.getJButton().setVisible(false);
        }
    }

    private void InitialCardPass() {
        //One everything is created a ready to go, go through each card one time and make sure it is updated
        for (String cardName: cardsMap.keySet()) {
            Card card = cardsMap.get(cardName);

            card.Update();
        }
    }

    private void CreateListOfButtons() {
        listButtons = new ArrayList<>();
        listButtons.add(new ButtonObject("Info/Race"));
        listButtons.add(new ButtonObject("Attributes"));
        listButtons.add(new ButtonObject("Skills"));

        buttonMagic = new ButtonObject("Magic");
        listButtons.add(buttonMagic);
        
        //listButtons.add(new ButtonObject("Save/Export"));
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
        cardsMap.put("Skills", new SkillsCard(characterContainer));
        cardsMap.put(("Magic"), new MagicCard(characterContainer));
        //cardsMap.put("Save/Export", new SaveCard(characterContainer));

        selectedCard = cardsMap.get("Info/Race"); //Ideal will get whatever one is first but here we have to specify
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
        System.out.println("CARD NAME: " + cardName);
        System.out.println("SELECTED CARD TITLE: " + selectedCard.getTitle());

        if (selectedCard.getTitle().equalsIgnoreCase(cardName)) {
            return;
        }

        System.out.println("CharacterCreationWindow: Switching To -> " + cardName);

        //Update the current card before switching
        selectedCard.Update();

        //Change which card is selected
        selectedCard = cardsMap.get(cardName);
        cardLayout.show(panelCards, cardName);

        selectedCard.Update();

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
