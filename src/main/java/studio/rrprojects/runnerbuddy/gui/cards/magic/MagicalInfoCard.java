package studio.rrprojects.runnerbuddy.gui.cards.magic;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.constants.MagicConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.magic.AspectContainer;
import studio.rrprojects.runnerbuddy.containers.magic.magetype.MagicUserContainer;
import studio.rrprojects.runnerbuddy.containers.magic.magetype.SpellCasterContainer;
import studio.rrprojects.runnerbuddy.containers.magic.dominions.DominionContainer;
import studio.rrprojects.runnerbuddy.containers.priority.ListPriority;
import studio.rrprojects.runnerbuddy.controllers.MagicController;
import studio.rrprojects.runnerbuddy.gui.cards.Card;
import studio.rrprojects.runnerbuddy.gui.cards.components.BasicComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MagicalInfoCard extends Card {
    private final CharacterContainer characterContainer;
    private JPanel panelMain;
    private JPanel panelMagic;
    private JPanel panelMagicBoxes;
    private JPanel panelMagicDescription;
    private JTextArea textAreaDescription;
    private BasicComboBox boxMageType;
    private BasicComboBox boxMagicTradition;
    private BasicComboBox boxMagicAspect;
    private BasicComboBox boxDominionSelection;

    private MagicUserContainer currentlySelectedType = null;
    private String currentlySelectedTradition = null;
    private AspectContainer currentlySelectedAspect = null;
    private DominionContainer currentlySelectedDominion = null;

    public MagicalInfoCard(String magic_info, CharacterContainer characterContainer) {
        super(magic_info);
        this.characterContainer = characterContainer;
        setPanel(panelMain);

        FormatBoxPanel();
        PopulateMageTypeBox();
    }

    private void PopulateMageTypeBox() {
        ListPriority magicPriority = characterContainer.getMagicController().getSelectedPriority();
        ArrayList<String> availableOptions = magicPriority.getAvailableOptions();
        MagicController magicController = characterContainer.getMagicController();

        for (String magicType : availableOptions) {
            MagicUserContainer magicUser = magicController.getMagicUserTypeMap().get(magicType);

            if (magicUser != null) {
                boxMageType.addOption(magicUser);
            }
        }
    }

    private void FormatBoxPanel() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setColumns(0);
        gridLayout.setRows(2);
        panelMagicBoxes.setLayout(gridLayout);

        boxMageType = CreateComboBox("Mage Type");
        boxMagicTradition = CreateComboBox("Tradition");
        boxMagicAspect = CreateComboBox("Aspect");
        boxDominionSelection = CreateComboBox("Totem/Element");
    }

    private BasicComboBox CreateComboBox(String title) {
        BasicComboBox combobox = new BasicComboBox(title);
        combobox.addEvenet(this);
        panelMagicBoxes.add(combobox.getPanel());
        return combobox;
    }

    @Override
    public void Update() {
        // Check Mage Type vs currentlySelected
        if (boxMageType.getSelectedItem() != currentlySelectedType) {
            currentlySelectedType = (MagicUserContainer) boxMageType.getSelectedItem();
            System.out.println("Changed Mage Type!");
            PopulateTraditionBox();
        }

        // Do the same for tradition
        if (boxMagicTradition.getSelectedItem() != null && !boxMagicTradition.getSelectedItem().toString().equalsIgnoreCase(currentlySelectedTradition)) {
            currentlySelectedTradition = boxMagicTradition.getSelectedItem().toString();
            System.out.println("Changed Mage Tradition!");
            PopulateAspectBox();
        }

        // Continue for aspect
        if (boxMagicAspect.getSelectedItem() != null && boxMagicAspect.getSelectedItem() != currentlySelectedAspect) {
            currentlySelectedAspect = (AspectContainer) boxMagicAspect.getSelectedItem();
            System.out.println("Changed Magic Aspect Tradition!");
            PopulateDomainBox();
        }

        // Finally for Dominion (Totem/Element)
        if (boxDominionSelection.getSelectedItem() != null && boxDominionSelection.getSelectedItem() != currentlySelectedDominion) {
            currentlySelectedDominion = (DominionContainer) boxDominionSelection.getSelectedItem();
            System.out.println("Changed Dominion");
        }

        PopulateDescription();

    }

    private void PopulateDescription() {
        if (currentlySelectedDominion == null) {
            System.out.println("CLEARING TEXT DESCRIPTION");
            textAreaDescription.setText("");
            return;
        }

        String description = currentlySelectedDominion.getDescription();

        textAreaDescription.setText(description);
    }

    private void PopulateDomainBox() {
        boxDominionSelection.clearOptions();
        boxDominionSelection.setBoxEnabled(true);

        if (currentlySelectedAspect.containsTag(MagicConstants.TOTEM)) {
            ArrayList<DominionContainer> listTotems = characterContainer.getMagicController().getMapDominions().get(MagicConstants.TOTEM);
            for (DominionContainer totem : listTotems) {
                boxDominionSelection.addOption(totem);
            }

        } else if (currentlySelectedAspect.containsTag(MagicConstants.ELEMENT)) {
            System.out.println("DING ELEMENTALIST!");
            ArrayList<DominionContainer> listElements = characterContainer.getMagicController().getMapDominions().get(MagicConstants.ELEMENT);
            for (DominionContainer element : listElements) {
                boxDominionSelection.addOption(element);
            }
        } else {
            boxDominionSelection.setBoxEnabled(false);
        }
    }

    private void PopulateAspectBox() {
        boxMagicAspect.clearOptions();
        SpellCasterContainer spellCasterContainer = (SpellCasterContainer) currentlySelectedType;
        ArrayList<String> listOptions = spellCasterContainer.getTraditionOptions().get(currentlySelectedTradition);

        System.out.println(listOptions.toString());

        LinkedHashMap<String, AspectContainer> mapAspects = characterContainer.getMagicController().getMapAspects();

        for (String aspectName : listOptions) {
            boxMagicAspect.addOption(mapAspects.get(aspectName));
        }
    }

    private void PopulateTraditionBox() {
        if (!currentlySelectedType.getMageType().equalsIgnoreCase(MagicConstants.ADEPT)) {
            boxMagicTradition.setOptions(new String[]{MagicConstants.HERMETIC, MagicConstants.SHAMANIC});
        }
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
        panelMain.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMagic = new JPanel();
        panelMagic.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelMagic, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(320, 44), null, 0, false));
        panelMagicBoxes = new JPanel();
        panelMagicBoxes.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        panelMagic.add(panelMagicBoxes, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelMagicDescription = new JPanel();
        panelMagicDescription.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        panelMagic.add(panelMagicDescription, new GridConstraints(1, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textAreaDescription = new JTextArea();
        textAreaDescription.setEditable(false);
        textAreaDescription.setLineWrap(true);
        textAreaDescription.setWrapStyleWord(true);
        panelMagicDescription.add(textAreaDescription, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMagic.add(spacer1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMagic.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
