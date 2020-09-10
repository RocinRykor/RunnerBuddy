package studio.rrprojects.runnerbuddy.gui;

import studio.rrprojects.runnerbuddy.gui.cards.AttributeCard;
import studio.rrprojects.runnerbuddy.gui.cards.GearCard;
import studio.rrprojects.runnerbuddy.gui.cards.NameCard;
import studio.rrprojects.runnerbuddy.gui.cards.SkillCard;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.RunnerBuilderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramWindow extends JFrame {

    private JPanel panelMain;
    private JPanel panelMenu;
    private JPanel panelCards;
    private JPanel cardName;
    private JButton buttonName, buttonAttr, buttonSkills, buttonGear, buttonContacts;
    private JPanel cardAttr;
    CardLayout cardLayout;
    CharacterContainer characterContainer;
    private NameCard nameCard;
    private AttributeCard attrCard;
    private SkillCard skillsCard;
    private JPanel cardSkills;
    private GearCard gearCard;
    private JPanel cardGear;

    public ProgramWindow(String title, RunnerBuilderController runnerBuilderController, CharacterContainer characterContainer) {
        super(title);
        this.characterContainer = characterContainer;
        BeginInit();
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        AddCardsToCharacterContainer();
    }

    private void AddCardsToCharacterContainer() {
        //characterContainer.setCardName(nameCard);
        //characterContainer.setCardAttr(attrCard);
    }

    private void BeginInit() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout(0, 0));

        //Split Panel
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(30);
        splitPane1.setDividerSize(0);
        splitPane1.setOrientation(0);
        panelMain.add(splitPane1, BorderLayout.CENTER);

        //Buttons
        buttonName = new JButton("Name");
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardName");
            }
        });

        buttonAttr = new JButton("Attributes");
        buttonAttr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardAttr");
            }
        });

        buttonSkills = new JButton("Skills");
        buttonSkills.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardSkills");
            }
        });

        buttonGear = new JButton("Gear");
        buttonGear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardGear");
            }
        });

        buttonContacts = new JButton("Contacts");
        buttonContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardContacts");
            }
        });

        //Menu Panels
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());
        panelMenu.add(buttonName);
        panelMenu.add(buttonAttr);
        panelMenu.add(buttonSkills);
        panelMenu.add(buttonGear);
        panelMenu.add(buttonContacts);
        splitPane1.setLeftComponent(panelMenu);

        //panelCards
        panelCards = new JPanel();
        panelCards.setLayout(new CardLayout(0, 0));
        panelCards.setPreferredSize(new Dimension(800, 700));
        splitPane1.setRightComponent(panelCards);

        //Cards
        nameCard = new NameCard(characterContainer);
        cardName = nameCard.getPanel();
        panelCards.add(cardName, "CardName");

        attrCard = new AttributeCard(characterContainer);
        cardAttr = attrCard.getPanel();
        panelCards.add(cardAttr, "CardAttr");
        cardLayout = (CardLayout) panelCards.getLayout();

        skillsCard = new SkillCard(characterContainer);
        cardSkills = skillsCard.getPanel();
        panelCards.add(cardSkills, "CardSkills");
        cardLayout = (CardLayout) panelCards.getLayout();
        
        gearCard = new GearCard(characterContainer);
        cardGear = gearCard.getPanel();
        panelCards.add(cardGear, "CardGear");
        cardLayout = (CardLayout) panelCards.getLayout();

    }

    private void SwitchTo(String card) {
        cardLayout.show(panelCards, card);
        repaint();
    }
}
