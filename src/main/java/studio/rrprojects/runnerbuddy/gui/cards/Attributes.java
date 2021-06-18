package studio.rrprojects.runnerbuddy.gui.cards;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.AttributeController;
import studio.rrprojects.runnerbuddy.gui.cards.components.*;
import studio.rrprojects.runnerbuddy.utils.MiscUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Attributes extends Card {
    private final CharacterContainer characterContainer;
    private JPanel panelMain;
    private SmallProgressBar progressBarAttributes;
    private JPanel panelAttributes;
    private JLabel labelSelectedRace;

    private String selectedRace = "None";
    private LinkedHashMap<String, AttributeModule> attributeMap;

    public Attributes(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;
        setPanel(panelMain);

        progressBarAttributes.setTitle("Attribute Points");
        progressBarAttributes.setMin(0);

        int maxAttributes = characterContainer.getAttributeController().getSelectedPriority().getValueInt();

        progressBarAttributes.setMax(maxAttributes);
        progressBarAttributes.setValue(6);

        FormatAttributesPanel();
    }

    public void UpdateProgressBar() {
        int allotedPoints = 0;

        ArrayList<String> basicAttributes = MiscUtils.basicAttributes();
        for (String attribute : basicAttributes) {
            AttributeModule attributeMod = attributeMap.get(attribute);
            allotedPoints += attributeMod.getAllottedPoints();
        }

        progressBarAttributes.setValue(allotedPoints);
        int currentPoints = progressBarAttributes.getValue();
        int maxPoints = progressBarAttributes.getValueMax();

        if (currentPoints >= maxPoints) {
            DisablePlusButtons();
        } else {
            RestorePlusButtons();
        }
    }

    private void RestorePlusButtons() {
        for (Map.Entry<String, AttributeModule> attributeKey : attributeMap.entrySet()) {
            attributeKey.getValue().RestorePlusButtons();
        }
    }

    private void DisablePlusButtons() {
        for (Map.Entry<String, AttributeModule> attributeKey : attributeMap.entrySet()) {
            attributeKey.getValue().DisablePlusButtons();
        }
    }

    private void FormatAttributesPanel() {
        GridLayout layout = new GridLayout();
        layout.setColumns(1);
        layout.setRows(0);
        panelAttributes.setLayout(layout);

        attributeMap = new LinkedHashMap<>();
        //Basic Attributes
        ArrayList<String> basicAttributes = MiscUtils.basicAttributes();

        for (String attribute : basicAttributes) {
            AttributeModule attributeModule = new AttributeModule();
            attributeModule.UpdateValues();
            panelAttributes.add(attributeModule.getPanel());

            attributeModule.LinkAttribute(characterContainer, attribute, this);

            attributeMap.put(attribute, attributeModule);
        }

        //Custom Attributes

        //Reaction
        AttributeModule reactionModule = new ReactionModule();
        panelAttributes.add(reactionModule.getPanel());
        reactionModule.LinkAttribute(characterContainer, "Reaction", this);
        attributeMap.put("Reaction", reactionModule);

        //Essence
        AttributeModule essenceModule = new EssenceModule();
        panelAttributes.add(essenceModule.getPanel());
        essenceModule.LinkAttribute(characterContainer, "Essence", this);
        attributeMap.put("Essence", essenceModule);

        //Magic
        System.out.println("ATTRIBUTES: " + characterContainer);
        AttributeModule magicModule = new MagicModule(characterContainer, "Magic", this);
        panelAttributes.add(magicModule.getPanel());
        attributeMap.put("Magic", magicModule);


        panelMain.repaint();
        panelAttributes.repaint();
    }

    @Override
    public void Update() {
        super.Update();
        String raceName = characterContainer.getRaceController().getSelectedRace().getName();
        if (!selectedRace.equalsIgnoreCase(raceName)) {
            labelSelectedRace.setText("Race: " + TextUtils.titleCase(raceName));
            UpdateRacialMods();
        }
    }

    private void UpdateRacialMods() {
        ArrayList<String> basicAttributes = MiscUtils.basicAttributes();
        LinkedHashMap<String, Integer> modMap = characterContainer.getRaceController().getSelectedRace().getModifiersAttributes();

        for (String attribute : basicAttributes) {
            int modValue = 0;

            try {
                modValue = modMap.get(attribute);
            } catch (NullPointerException ignored) {
            }

            attributeMap.get(attribute).setRacialMod(modValue);
        }

    }

    public LinkedHashMap<String, AttributeModule> getAttributeMap() {
        return attributeMap;
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
        panelMain.setLayout(new GridLayoutManager(3, 10, new Insets(10, 10, 10, 10), -1, -1));
        labelSelectedRace = new JLabel();
        labelSelectedRace.setText("Race: X");
        panelMain.add(labelSelectedRace, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Attribute Name");
        panelMain.add(label1, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Allotted Points");
        panelMain.add(label2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Racial Modifier");
        panelMain.add(label3, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Total Points");
        panelMain.add(label4, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Add/Remove");
        panelMain.add(label5, new GridConstraints(1, 9, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelAttributes = new JPanel();
        panelAttributes.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelAttributes, new GridConstraints(2, 0, 1, 10, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        progressBarAttributes = new SmallProgressBar();
        panelMain.add(progressBarAttributes.$$$getRootComponent$$$(), new GridConstraints(0, 3, 1, 7, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panelMain.add(separator1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        panelMain.add(separator2, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator3 = new JSeparator();
        panelMain.add(separator3, new GridConstraints(1, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator4 = new JSeparator();
        panelMain.add(separator4, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    public void UpdateAll() {
        for (Map.Entry<String, AttributeModule> attributeModule : attributeMap.entrySet()) {
            attributeModule.getValue().UpdateValues();
        }
    }
}
