package studio.rrprojects.runnerbuddy.gui;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardManager {

    private final JPanel workingPanel;
    private int defaultDividerSize = 50;
    private JSplitPane splitPane;
    private JPanel panelMenu;
    private JPanel panelCards;
    private ArrayList<Card> listCards = new ArrayList<>();
    private CharacterContainer characterContainer;
    private Card selectedCard;
    private CardLayout cardLayout;

    public CardManager(JPanel jPanel) {
        workingPanel = jPanel;

        SetPanels();
    }

    private void SetPanels() {
        //Main
        workingPanel.setLayout(new BorderLayout(0, 0));

        //Split Panel
        splitPane = new JSplitPane();
        splitPane.setDividerLocation(defaultDividerSize);
        splitPane.setDividerSize(0);
        splitPane.setOrientation(0);
        workingPanel.add(splitPane, BorderLayout.CENTER);

        //Menu Panels
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());

        splitPane.setLeftComponent(panelMenu);

        //Card Panel
        panelCards = new JPanel();
        cardLayout = new CardLayout(0,0);
        panelCards.setLayout(cardLayout);
        panelCards.setPreferredSize(workingPanel.getPreferredSize());
        splitPane.setRightComponent(panelCards);

        //
    }

    public void addCard(Card card){
        listCards.add(card);

        JButton button = card.getButton();
        button.addActionListener(actionEvent -> {
            SwtichToThisCard(card);
        });;
        panelMenu.add(button);

        panelCards.add(card.getPanel(), card.getTitle());

        card.setCharacterContainer(characterContainer);

        card.Update();
    }

    private void SwtichToThisCard(Card card) {
        //Update the current card before switching
        //selectedCard.Update();

        //Change which card is selected
        selectedCard = card;
        cardLayout.show(panelCards, card.getTitle());

        selectedCard.Update();
    }

    public void setCharacterContainer(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
    }
}
