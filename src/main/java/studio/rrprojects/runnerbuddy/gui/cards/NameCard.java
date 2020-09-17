package studio.rrprojects.runnerbuddy.gui.cards;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class NameCard {
    private JPanel panelMain;
    private JTextField textAge;
    private JComboBox<String> boxRaceLevel;
    private JLabel labelNameReal;
    private JLabel labelAge;
    private JLabel labelSex;
    private JLabel labelRaceLevel;
    private JLabel labelMagicLevel;
    private JLabel labelAttr;
    private JLabel labelSkills;
    private JTextField textSex;
    private JComboBox<String> boxMagicLevel;
    private JComboBox<String> boxAttr;
    private JComboBox<String> boxSkills;
    private JLabel labelResources;
    private JComboBox<String> boxResources;
    private JComboBox<String> boxRace;
    private JComboBox<String> boxMagic;
    private JLabel labelName;
    private JTextField textName;
    private JTextField textNameReal;
    private JLabel labelValidOutput;
    CharacterContainer characterContainer;
    Boolean isValid;
    ArrayList<JComboBox> boxGroup;

    public NameCard(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        Init();
        PopulateBoxes();
        boxRaceLevel.addActionListener(actionEvent -> {
            UpdateRaceBox();
            CheckValidity();
        });
        boxMagicLevel.addActionListener(actionEvent -> {
            UpdateMagicBox();
            CheckValidity();
        });
        boxRace.addActionListener(actionEvent -> {
            CheckValidity();
            characterContainer.getRaceController().setSelectedRace(Objects.requireNonNull(boxRace.getSelectedItem()));
        });
        boxMagic.addActionListener(actionEvent -> {
            CheckValidity();
            characterContainer.getMagicController().setSelectedMagicLevel((String) boxMagic.getSelectedItem());
        });

        boxAttr.addActionListener(actionEvent -> {
            CheckValidity();
            characterContainer.getAttributeController().setSelectedAttributePriority(Objects.requireNonNull(boxAttr.getSelectedItem()));
        });
        boxSkills.addActionListener(actionEvent -> {
            CheckValidity();
            characterContainer.getSkillsController().setBaseSkillPoints(Objects.requireNonNull(boxSkills.getSelectedItem()).toString());
        });
        boxResources.addActionListener(actionEvent -> CheckValidity());
    }

    private void Init() {
        isValid = false;
        boxGroup = new ArrayList<>();
        boxGroup.add(boxRaceLevel);
        boxGroup.add(boxMagicLevel);
        boxGroup.add(boxAttr);
        boxGroup.add(boxSkills);
        boxGroup.add(boxResources);
    }

    private void CheckValidity() {
        ArrayList<String> takenPriorities = new ArrayList<>();
        takenPriorities.add("A");
        takenPriorities.add("B");
        takenPriorities.add("C");
        takenPriorities.add("D");
        takenPriorities.add("E");

        for (JComboBox box : boxGroup) {
            String searchTerm = String.valueOf(Objects.requireNonNull(box.getSelectedItem()).toString().charAt(0));
            takenPriorities.remove(searchTerm);
        }

        SetValidity(takenPriorities.isEmpty(), takenPriorities);
    }

    private void SetValidity(boolean valid, ArrayList<String> takenPriorities) {
        isValid = valid;
        if (valid) {
            labelValidOutput.setForeground(new Color(-11805347));
            labelValidOutput.setText("VALID");
        } else {
            labelValidOutput.setForeground(Color.RED);
            labelValidOutput.setText(String.format("INVALID | LEVELS %s NOT TAKEN", takenPriorities.toString()));
        }
    }

    private void UpdateMagicBox() {
        String input = Objects.requireNonNull(boxMagicLevel.getSelectedItem()).toString();

        if (input.startsWith("--")) {
            DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
            box.addElement("-- Select Magic --");
            boxMagic.setModel(box);
            return;
        }

        boxMagic.setModel(characterContainer.getMagicController().SearchSubMenu(input).GetPriorityBox());
    }

    private void UpdateRaceBox() {
        String input = Objects.requireNonNull(boxRaceLevel.getSelectedItem()).toString();

        if (input.startsWith("--")) {
            DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
            box.addElement("-- Select A Race --");
            boxRace.setModel(box);
            return;
        }

        boxRace.setModel(characterContainer.getRaceController().SearchSubMenu(input).GetPriorityBox());
    }

    private void PopulateBoxes() {
        boxRaceLevel.setModel(characterContainer.getRaceController().GetPriorityBox());
        boxMagicLevel.setModel(characterContainer.getMagicController().GetPriorityBox());
        boxAttr.setModel(characterContainer.getAttributeController().GetPriorityBox());
        boxSkills.setModel(characterContainer.getSkillsController().GetPriorityBox());
        boxResources.setModel(characterContainer.getResourceController().GetPriorityBox());
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
        panelMain.setLayout(new GridLayoutManager(13, 7, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setAlignmentX(1.0f);
        panelMain.setAlignmentY(1.0f);
        panelMain.setBackground(new Color(-14079180));
        panelMain.setForeground(new Color(-11805347));
        labelNameReal = new JLabel();
        labelNameReal.setForeground(new Color(-11805347));
        labelNameReal.setText("Real Name");
        panelMain.add(labelNameReal, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMagicLevel = new JLabel();
        labelMagicLevel.setForeground(new Color(-11805347));
        labelMagicLevel.setText("Magic Level");
        panelMain.add(labelMagicLevel, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        boxMagicLevel = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("-- Select Magic Level --");
        boxMagicLevel.setModel(defaultComboBoxModel1);
        panelMain.add(boxMagicLevel, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textNameReal = new JTextField();
        panelMain.add(textNameReal, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelSex = new JLabel();
        labelSex.setForeground(new Color(-11805347));
        labelSex.setText("Sex");
        panelMain.add(labelSex, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textSex = new JTextField();
        panelMain.add(textSex, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelRaceLevel = new JLabel();
        labelRaceLevel.setForeground(new Color(-11805347));
        labelRaceLevel.setText("Race Level");
        panelMain.add(labelRaceLevel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-11805347));
        label1.setText("Race");
        panelMain.add(label1, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxRaceLevel = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("-- Select Race --");
        boxRaceLevel.setModel(defaultComboBoxModel2);
        panelMain.add(boxRaceLevel, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxRace = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("-- Select Race --");
        boxRace.setModel(defaultComboBoxModel3);
        panelMain.add(boxRace, new GridConstraints(5, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(-11805347));
        label2.setText("Magic");
        panelMain.add(label2, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxMagic = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel4 = new DefaultComboBoxModel();
        defaultComboBoxModel4.addElement("-- Select Magic Level --");
        boxMagic.setModel(defaultComboBoxModel4);
        panelMain.add(boxMagic, new GridConstraints(7, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelSkills = new JLabel();
        labelSkills.setForeground(new Color(-11805347));
        labelSkills.setText("Skills");
        panelMain.add(labelSkills, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(12, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelAttr = new JLabel();
        labelAttr.setForeground(new Color(-11805347));
        labelAttr.setText("Attributes");
        panelMain.add(labelAttr, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        boxAttr = new JComboBox();
        boxAttr.setEnabled(true);
        final DefaultComboBoxModel defaultComboBoxModel5 = new DefaultComboBoxModel();
        defaultComboBoxModel5.addElement("-- Select Attribute Level --");
        boxAttr.setModel(defaultComboBoxModel5);
        panelMain.add(boxAttr, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxSkills = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel6 = new DefaultComboBoxModel();
        defaultComboBoxModel6.addElement("-- Select Skills Level --");
        boxSkills.setModel(defaultComboBoxModel6);
        panelMain.add(boxSkills, new GridConstraints(9, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelResources = new JLabel();
        labelResources.setForeground(new Color(-11805347));
        labelResources.setText("Resources");
        panelMain.add(labelResources, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxResources = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel7 = new DefaultComboBoxModel();
        defaultComboBoxModel7.addElement("-- Select Resources Level --");
        boxResources.setModel(defaultComboBoxModel7);
        panelMain.add(boxResources, new GridConstraints(11, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelName = new JLabel();
        labelName.setBackground(new Color(-14801108));
        labelName.setForeground(new Color(-11805347));
        labelName.setText("Nickname");
        panelMain.add(labelName, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textAge = new JTextField();
        panelMain.add(textAge, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelAge = new JLabel();
        labelAge.setForeground(new Color(-11805347));
        labelAge.setText("Age");
        panelMain.add(labelAge, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textName = new JTextField();
        panelMain.add(textName, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panelMain.add(separator1, new GridConstraints(4, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelMain.add(spacer4, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelMain.add(spacer5, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelMain.add(spacer6, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        separator2.setOrientation(0);
        panelMain.add(separator2, new GridConstraints(0, 0, 1, 7, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panelMain.add(spacer7, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelValidOutput = new JLabel();
        labelValidOutput.setForeground(new Color(-11805347));
        labelValidOutput.setText("VALID");
        panelMain.add(labelValidOutput, new GridConstraints(11, 4, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelRaceLevel.setLabelFor(boxRaceLevel);
        label1.setLabelFor(boxRaceLevel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    public JPanel getPanel() {
        return panelMain;
    }
}
