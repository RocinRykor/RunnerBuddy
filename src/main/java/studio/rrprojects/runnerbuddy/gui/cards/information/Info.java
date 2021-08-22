package studio.rrprojects.runnerbuddy.gui.cards.information;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.containers.RaceContainer;
import studio.rrprojects.runnerbuddy.controllers.DescriptionController;
import studio.rrprojects.runnerbuddy.gui.cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Info extends Card {
    private JPanel panelMain;
    private JPanel panelRace;
    private JPanel panelInfo;
    private JPanel panelRaceButtons;
    private JScrollPane panelRaceDescription;
    private JTextArea textRaceDescription;
    private JPanel panelNames;
    private JPanel panelDescription;
    private JTextField textNameReal;
    private JTextField textGender;
    private JTextArea textAreaNotes;
    private JTextField textEyes;
    private JTextField textWeight;
    private JTextField textHeight;
    private JTextField textHair;
    private JTextField textSkin;
    private JTextField textNameStreet;

    private ButtonGroup buttonGroup;
    private ArrayList<JTextField> textFieldList;
    private ArrayList<String> raceNames;

    public Info(String title) {
        super(title);
        setPanel(panelMain);
    }

    @Override
    public void Initialize() {
        super.Initialize();

        FormatRacePanel();
        FormatInfoPanel();

        SetDefaultRace();
    }

    private void SetDefaultRace() {
        String selectedRace = raceNames.get(0);

        SelectRace(selectedRace); //Selects the first available race by default

        for (Iterator<AbstractButton> it = buttonGroup.getElements().asIterator(); it.hasNext(); ) {
            AbstractButton button = it.next();
            if (button.getActionCommand().equalsIgnoreCase(selectedRace)) {
                button.setSelected(true);
            }
        }
    }

    private void SaveDescription() {
        DescriptionController descriptionController = getCharacterContainer().getDescriptionController();

        descriptionController.setNameReal(textNameReal.getText());
        descriptionController.setNameStreet(textNameStreet.getText());
        descriptionController.setGender(textGender.getText());
        descriptionController.setEyes(textEyes.getText());
        descriptionController.setWeight(textWeight.getText());
        descriptionController.setHeight(textHeight.getText());
        descriptionController.setHair(textHair.getText());
        descriptionController.setSkin(textSkin.getText());
        descriptionController.setNotes(textAreaNotes.getText());
    }

    private void FormatInfoPanel() {
        panelDescription.setBorder(BorderFactory.createTitledBorder("Description"));
        panelNames.setBorder(BorderFactory.createTitledBorder("Names"));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Character Information"));

        textFieldList = new ArrayList<>();
        textFieldList.add(textNameReal);
        textFieldList.add(textNameStreet);
        textFieldList.add(textGender);
        textFieldList.add(textEyes);
        textFieldList.add(textWeight);
        textFieldList.add(textHeight);
        textFieldList.add(textHair);
        textFieldList.add(textSkin);
    }

    private void FormatRacePanel() {
        panelRace.setBorder(BorderFactory.createTitledBorder("Select a Race"));
        panelRaceButtons.setBorder(BorderFactory.createTitledBorder("Race"));
        panelRaceDescription.setBorder(BorderFactory.createTitledBorder("Race Modifiers"));

        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(0);
        gridLayout.setRows(1);
        panelRaceButtons.setLayout(gridLayout);
        buttonGroup = new ButtonGroup();


        raceNames = getCharacterContainer().getRaceController().getAvailibleRaces();

        for (String race : raceNames) {
            JRadioButton button = new JRadioButton(race);
            button.addActionListener(actionEvent -> {
                SelectRace(button.getActionCommand());
            });

            button.setHorizontalAlignment(SwingConstants.CENTER);

            buttonGroup.add(button);
            panelRaceButtons.add(button);
        }

    }

    private void SelectRace(String raceName) {
        getCharacterContainer().getRaceController().setSelectedRace(raceName);
        RaceContainer selectedRace = getCharacterContainer().getRaceController().getSelectedRace();

        SetDescription(selectedRace.getDescription());
    }

    @Override
    public void Update() {
        super.Update();
        SaveDescription();
    }

    private void SetDescription(String baseDescription) {
        String PriorityMap = getCharacterContainer().getPriorityController().getPriorityMapString();

        String description = baseDescription + "\n" + PriorityMap;

        textRaceDescription.setText(description);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(2, 3, new Insets(10, 10, 10, 10), -1, -1));
        panelRace = new JPanel();
        panelRace.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelRace, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(320, 44), null, 0, false));
        panelRaceDescription = new JScrollPane();
        panelRace.add(panelRaceDescription, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textRaceDescription = new JTextArea();
        textRaceDescription.setEditable(false);
        panelRaceDescription.setViewportView(textRaceDescription);
        panelRaceButtons = new JPanel();
        panelRaceButtons.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelRace.add(panelRaceButtons, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 60), new Dimension(-1, 60), new Dimension(-1, 60), 0, false));
        panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelInfo, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelDescription = new JPanel();
        panelDescription.setLayout(new GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        panelInfo.add(panelDescription, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Gender");
        panelDescription.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelDescription.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        textGender = new JTextField();
        panelDescription.add(textGender, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Eyes");
        panelDescription.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textEyes = new JTextField();
        panelDescription.add(textEyes, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Weight");
        panelDescription.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textWeight = new JTextField();
        panelDescription.add(textWeight, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Height");
        panelDescription.add(label4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textHeight = new JTextField();
        panelDescription.add(textHeight, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Hair");
        panelDescription.add(label5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textHair = new JTextField();
        panelDescription.add(textHair, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Skin");
        panelDescription.add(label6, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textSkin = new JTextField();
        panelDescription.add(textSkin, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Notes:");
        panelDescription.add(label7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textAreaNotes = new JTextArea();
        textAreaNotes.setLineWrap(true);
        textAreaNotes.setWrapStyleWord(true);
        panelDescription.add(textAreaNotes, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        panelNames = new JPanel();
        panelNames.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panelInfo.add(panelNames, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textNameReal = new JTextField();
        textNameReal.setText("Joe");
        panelNames.add(textNameReal, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Real Name:");
        panelNames.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textNameStreet = new JTextField();
        textNameStreet.setText("Bob");
        panelNames.add(textNameStreet, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Street Name:");
        panelNames.add(label9, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
