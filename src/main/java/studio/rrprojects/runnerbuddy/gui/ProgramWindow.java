package studio.rrprojects.runnerbuddy.gui;

import studio.rrprojects.runnerbuddy.gui.cards.*;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.RunnerBuilderController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramWindow extends JFrame {

    private JPanel panelMain, panelMenu, panelCards;
    private JButton buttonName, buttonAttr, buttonSkills, buttonGear, buttonMagic, buttonContacts;
    private JPanel cardName, cardAttr, cardSkills, cardGear, cardContacts, cardMagic;

    CardLayout cardLayout;
    CharacterContainer characterContainer;

    //Cards
    private NameCard nameCard;
    private AttributeCard attrCard;
    private SkillCard skillsCard;
    private GearCard gearCard;
    private ContactsCard contactsCard;
    private MagicCard magicCard;

    public ProgramWindow(String title, RunnerBuilderController runnerBuilderController, CharacterContainer characterContainer) {
        super(title);
        this.characterContainer = characterContainer;
        BeginInit();
        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void BeginInit() {
        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout(0, 0));

        //Split Panel
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(40);
        splitPane1.setDividerSize(0);
        splitPane1.setOrientation(0);
        panelMain.add(splitPane1, BorderLayout.CENTER);

        //Buttons
        buttonName = new JButton("Name");
        buttonName.setBackground(new Color(-14079180));
        buttonName.setForeground(new Color(-11805347));
        buttonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardName");
            }
        });

        buttonAttr = new JButton("Attributes");
        buttonAttr.setBackground(new Color(-14079180));
        buttonAttr.setForeground(new Color(-11805347));
        buttonAttr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardAttr");
            }
        });

        buttonSkills = new JButton("Skills");
        buttonSkills.setBackground(new Color(-14079180));
        buttonSkills.setForeground(new Color(-11805347));
        buttonSkills.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardSkills");
            }
        });

        buttonGear = new JButton("Gear");
        buttonGear.setBackground(new Color(-14079180));
        buttonGear.setForeground(new Color(-11805347));
        buttonGear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardGear");
            }
        });

        buttonMagic = new JButton("Magic");
        buttonMagic.setBackground(new Color(-14079180));
        buttonMagic.setForeground(new Color(-11805347));
        buttonMagic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardMagic");
            }
        });

        buttonContacts = new JButton("Contacts");
        buttonContacts.setBackground(new Color(-14079180));
        buttonContacts.setForeground(new Color(-11805347));
        buttonContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SwitchTo("CardContacts");
            }
        });

        //Menu Panels
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());
        panelMenu.setBackground(new Color(-14079180));
        panelMenu.setForeground(new Color(-11805347));
        panelMenu.add(buttonName);
        panelMenu.add(buttonAttr);
        panelMenu.add(buttonSkills);
        panelMenu.add(buttonGear);
        panelMenu.add(buttonMagic);
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

        skillsCard = new SkillCard(characterContainer);
        cardSkills = skillsCard.getPanel();
        panelCards.add(cardSkills, "CardSkills");
        
        gearCard = new GearCard(characterContainer);
        cardGear = gearCard.getPanel();
        panelCards.add(cardGear, "CardGear");

        magicCard = new MagicCard(characterContainer);
        cardMagic = magicCard.getPanel();
        panelCards.add(cardMagic, "CardMagic");

        contactsCard = new ContactsCard(characterContainer);
        cardContacts = contactsCard.getPanel();
        panelCards.add(cardContacts, "CardContacts");
        

        cardLayout = (CardLayout) panelCards.getLayout();

    }

    private void SwitchTo(String card) {
        cardLayout.show(panelCards, card);
        repaint();
    }
}
