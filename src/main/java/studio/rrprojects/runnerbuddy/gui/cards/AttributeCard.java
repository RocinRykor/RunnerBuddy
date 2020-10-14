package studio.rrprojects.runnerbuddy.gui.cards;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.AttributeController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AttributeCard {
    private JPanel panelMain;
    private JLabel labelBody;
    private JLabel labelBodyMod;
    private JLabel labelBodyTotal;
    private JLabel labelQuick;
    private JLabel labelQuickMod;
    private JLabel labelQuickTotal;
    private JLabel labelStr;
    private JLabel labelStrMod;
    private JLabel labelStrTotal;
    private JLabel labelCha;
    private JLabel labelChaMod;
    private JLabel labelChaTotal;
    private JLabel labelInt;
    private JLabel labelIntMod;
    private JLabel labelIntTotal;
    private JLabel labelWil;
    private JLabel labelWilMod;
    private JLabel labelWilTotal;
    private JLabel labelEssTotal;
    private JLabel labelMagTotal;
    private JLabel labelReaTotal;
    private JComboBox<Integer> boxBody;
    private JComboBox<Integer> boxQuick;
    private JComboBox<Integer> boxStr;
    private JComboBox<Integer> boxCha;
    private JComboBox<Integer> boxInt;
    private JComboBox<Integer> boxWil;
    private JLabel labelStarting;
    private JLabel labelRemaining;
    private JLabel labelRace;
    CharacterContainer characterContainer;

    LinkedHashMap<String, AttributeGroup> attributeGroupList;
    LinkedHashMap<String, Integer> totalAttributes;

    public AttributeCard(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        Init();

        PopulateBoxes();

        UpdateAll();

        boxBody.addActionListener(actionEvent -> UpdateAll());
        boxQuick.addActionListener(actionEvent -> UpdateAll());
        boxStr.addActionListener(actionEvent -> UpdateAll());
        boxCha.addActionListener(actionEvent -> UpdateAll());
        boxInt.addActionListener(actionEvent -> UpdateAll());
        boxWil.addActionListener(actionEvent -> UpdateAll());
    }

    private void UpdateAll() {
        UpdateBaseInfo();
        UpdateModAndTotal();
    }

    private void UpdateModAndTotal() {
        //Calculate Racial Attribute Mods
        if (characterContainer.getRaceController().getSelectedRace() != null) {
            String race = characterContainer.getRaceController().getSelectedRace();
            AttributeController.AttributeArray modArray = characterContainer.getAttributeController().getRacialModTable().get(race);
            labelBodyMod.setText(String.valueOf(modArray.getAttribute("Body")));
            labelQuickMod.setText(String.valueOf(modArray.getAttribute("Quickness")));
            labelStrMod.setText(String.valueOf(modArray.getAttribute("Strength")));
            labelChaMod.setText(String.valueOf(modArray.getAttribute("Charisma")));
            labelIntMod.setText(String.valueOf(modArray.getAttribute("Intelligence")));
            labelWilMod.setText(String.valueOf(modArray.getAttribute("Willpower")));
        }

        //Calculate Total
        for (Map.Entry<String, AttributeGroup> attribute : attributeGroupList.entrySet()) {
            int base = Integer.parseInt(Objects.requireNonNull(attribute.getValue().box.getSelectedItem()).toString());
            int mod = Integer.parseInt(attribute.getValue().modLabel.getText());
            int total = base + mod;

            attribute.getValue().totalLabel.setText(String.valueOf(total));
            totalAttributes.replace(attribute.getKey(), total);

            if (total < 0) {
                attribute.getValue().totalLabel.setForeground(new Color(0xFF0012));
            } else {
                attribute.getValue().totalLabel.setForeground(new Color(-11805347));
            }

        }

        //Update Character Container
        characterContainer.getAttributeController().getSelectedAttributes().UpdateBaseAttributes(totalAttributes);

        labelEssTotal.setText(String.valueOf(characterContainer.getAttributeController().getSelectedAttributes().getAttribute("Essence")));
        labelMagTotal.setText(String.valueOf(characterContainer.getAttributeController().getSelectedAttributes().getAttribute("Magic")));
        labelReaTotal.setText(String.valueOf(characterContainer.getAttributeController().getSelectedAttributes().getAttribute("Reaction")));
    }

    private void UpdateBaseInfo() {
        int starting = characterContainer.getAttributeController().getSelectedAttributePriority().getAttributePoints();
        labelStarting.setText("Starting Points: " + starting);

        int remaining = starting - CalcPointsSpent();
        labelRemaining.setText("Remaining Points: " + remaining);

        if (remaining < 0) {
            labelRemaining.setForeground(new Color(0xFF0012));
        } else {
            labelRemaining.setForeground(new Color(-11805347));
        }

        String race = characterContainer.getRaceController().getSelectedRace();
        labelRace.setText("Selected Race: " + race);
    }

    private int CalcPointsSpent() {
        int i = 0;
        for (Map.Entry<String, AttributeGroup> attribute : attributeGroupList.entrySet()) {
            i += Integer.parseInt(Objects.requireNonNull(attribute.getValue().box.getSelectedItem()).toString());
        }

        return i;
    }

    private void PopulateBoxes() {
        for (Map.Entry<String, AttributeGroup> attribute : attributeGroupList.entrySet()) {
            DefaultComboBoxModel<Integer> boxModel = new DefaultComboBoxModel<>();
            for (int i = 1; i < 7; i++) {
                boxModel.addElement(i);
            }
            attribute.getValue().box.setModel(boxModel);
        }
    }

    private void Init() {
        //Create Groups for Each Attribute
        //Add All Groups to a list
        attributeGroupList = new LinkedHashMap<>();
        attributeGroupList.put("Body", new AttributeGroup(labelBody, labelBodyMod, labelBodyTotal, boxBody));
        attributeGroupList.put("Quickness", new AttributeGroup(labelQuick, labelQuickMod, labelQuickTotal, boxQuick));
        attributeGroupList.put("Strength", new AttributeGroup(labelStr, labelStrMod, labelStrTotal, boxStr));
        attributeGroupList.put("Charisma", new AttributeGroup(labelCha, labelChaMod, labelChaTotal, boxCha));
        attributeGroupList.put("Intelligence", new AttributeGroup(labelInt, labelIntMod, labelIntTotal, boxInt));
        attributeGroupList.put("Willpower", new AttributeGroup(labelWil, labelWilMod, labelWilTotal, boxWil));

        totalAttributes = new LinkedHashMap<>();
        totalAttributes.put("Body", 1);
        totalAttributes.put("Quickness", 1);
        totalAttributes.put("Strength", 1);
        totalAttributes.put("Charisma", 1);
        totalAttributes.put("Intelligence", 1);
        totalAttributes.put("Willpower", 1);
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
        panelMain.setLayout(new GridLayoutManager(23, 8, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBackground(new Color(-14079180));
        labelBody = new JLabel();
        labelBody.setForeground(new Color(-11805347));
        labelBody.setText("Body");
        panelMain.add(labelBody, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelQuick = new JLabel();
        labelQuick.setForeground(new Color(-11805347));
        labelQuick.setText("Quickness");
        panelMain.add(labelQuick, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelStr = new JLabel();
        labelStr.setForeground(new Color(-11805347));
        labelStr.setText("Strength");
        panelMain.add(labelStr, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelCha = new JLabel();
        labelCha.setForeground(new Color(-11805347));
        labelCha.setText("Charisma");
        panelMain.add(labelCha, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelInt = new JLabel();
        labelInt.setForeground(new Color(-11805347));
        labelInt.setText("Intelligence");
        panelMain.add(labelInt, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelWil = new JLabel();
        labelWil.setForeground(new Color(-11805347));
        labelWil.setText("Willpower");
        panelMain.add(labelWil, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setForeground(new Color(-11805347));
        label1.setText("Essessence");
        panelMain.add(label1, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setForeground(new Color(-11805347));
        label2.setText("Magic");
        panelMain.add(label2, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(-11805347));
        label3.setText("Reaction");
        panelMain.add(label3, new GridConstraints(21, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelBodyMod = new JLabel();
        labelBodyMod.setForeground(new Color(-11805347));
        labelBodyMod.setText("0");
        panelMain.add(labelBodyMod, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelBodyTotal = new JLabel();
        labelBodyTotal.setForeground(new Color(-11805347));
        labelBodyTotal.setText("0");
        panelMain.add(labelBodyTotal, new GridConstraints(5, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxQuick = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        boxQuick.setModel(defaultComboBoxModel1);
        panelMain.add(boxQuick, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxStr = new JComboBox();
        panelMain.add(boxStr, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxCha = new JComboBox();
        panelMain.add(boxCha, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxInt = new JComboBox();
        panelMain.add(boxInt, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxWil = new JComboBox();
        panelMain.add(boxWil, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boxBody = new JComboBox();
        panelMain.add(boxBody, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setForeground(new Color(-11805347));
        label4.setText("Attribute");
        panelMain.add(label4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setForeground(new Color(-11805347));
        label5.setText("Alloted Points (Minimum 1)");
        panelMain.add(label5, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setForeground(new Color(-11805347));
        label6.setText("Racial Bonus");
        panelMain.add(label6, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setForeground(new Color(-11805347));
        label7.setText("Total (Minimum 1)");
        panelMain.add(label7, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelQuickMod = new JLabel();
        labelQuickMod.setForeground(new Color(-11805347));
        labelQuickMod.setText("0");
        panelMain.add(labelQuickMod, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelStrMod = new JLabel();
        labelStrMod.setForeground(new Color(-11805347));
        labelStrMod.setText("0");
        panelMain.add(labelStrMod, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelChaMod = new JLabel();
        labelChaMod.setForeground(new Color(-11805347));
        labelChaMod.setText("0");
        panelMain.add(labelChaMod, new GridConstraints(11, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelIntMod = new JLabel();
        labelIntMod.setForeground(new Color(-11805347));
        labelIntMod.setText("0");
        panelMain.add(labelIntMod, new GridConstraints(13, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelWilMod = new JLabel();
        labelWilMod.setForeground(new Color(-11805347));
        labelWilMod.setText("0");
        panelMain.add(labelWilMod, new GridConstraints(15, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelQuickTotal = new JLabel();
        labelQuickTotal.setForeground(new Color(-11805347));
        labelQuickTotal.setText("0");
        panelMain.add(labelQuickTotal, new GridConstraints(7, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelStrTotal = new JLabel();
        labelStrTotal.setForeground(new Color(-11805347));
        labelStrTotal.setText("0");
        panelMain.add(labelStrTotal, new GridConstraints(9, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelChaTotal = new JLabel();
        labelChaTotal.setForeground(new Color(-11805347));
        labelChaTotal.setText("0");
        panelMain.add(labelChaTotal, new GridConstraints(11, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelIntTotal = new JLabel();
        labelIntTotal.setForeground(new Color(-11805347));
        labelIntTotal.setText("0");
        panelMain.add(labelIntTotal, new GridConstraints(13, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelWilTotal = new JLabel();
        labelWilTotal.setForeground(new Color(-11805347));
        labelWilTotal.setText("0");
        panelMain.add(labelWilTotal, new GridConstraints(15, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelEssTotal = new JLabel();
        labelEssTotal.setForeground(new Color(-11805347));
        labelEssTotal.setText("0");
        panelMain.add(labelEssTotal, new GridConstraints(17, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelMagTotal = new JLabel();
        labelMagTotal.setForeground(new Color(-11805347));
        labelMagTotal.setText("0");
        panelMain.add(labelMagTotal, new GridConstraints(19, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelReaTotal = new JLabel();
        labelReaTotal.setForeground(new Color(-11805347));
        labelReaTotal.setText("0");
        panelMain.add(labelReaTotal, new GridConstraints(21, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panelMain.add(separator1, new GridConstraints(4, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        panelMain.add(separator2, new GridConstraints(2, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator3 = new JSeparator();
        panelMain.add(separator3, new GridConstraints(6, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator4 = new JSeparator();
        panelMain.add(separator4, new GridConstraints(8, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator5 = new JSeparator();
        panelMain.add(separator5, new GridConstraints(10, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator6 = new JSeparator();
        panelMain.add(separator6, new GridConstraints(12, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator7 = new JSeparator();
        panelMain.add(separator7, new GridConstraints(14, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator8 = new JSeparator();
        panelMain.add(separator8, new GridConstraints(16, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(123, 11), null, 0, false));
        final JSeparator separator9 = new JSeparator();
        separator9.setOrientation(1);
        panelMain.add(separator9, new GridConstraints(1, 0, 22, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator10 = new JSeparator();
        separator10.setOrientation(1);
        panelMain.add(separator10, new GridConstraints(1, 7, 22, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelStarting = new JLabel();
        labelStarting.setEnabled(true);
        labelStarting.setForeground(new Color(-11805347));
        labelStarting.setText("Starting Amount: X");
        panelMain.add(labelStarting, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelRemaining = new JLabel();
        labelRemaining.setForeground(new Color(-11805347));
        labelRemaining.setText("Remaining: X");
        panelMain.add(labelRemaining, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator11 = new JSeparator();
        panelMain.add(separator11, new GridConstraints(0, 0, 1, 8, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelRace = new JLabel();
        labelRace.setForeground(new Color(-11805347));
        labelRace.setText("Selected Race: X");
        panelMain.add(labelRace, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(22, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelMain.add(spacer4, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelMain.add(spacer5, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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

    private class AttributeGroup {
        JLabel mainLabel, modLabel, totalLabel;
        JComboBox<Integer> box;

        public AttributeGroup(JLabel mainLabel, JLabel modLabel, JLabel totalLabel, JComboBox<Integer> box) {
            this.mainLabel = mainLabel;
            this.modLabel = modLabel;
            this.totalLabel = totalLabel;
            this.box = box;
        }
    }
}
