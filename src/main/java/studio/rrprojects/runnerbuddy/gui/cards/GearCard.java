package studio.rrprojects.runnerbuddy.gui.cards;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.gear.GearContainer;
import studio.rrprojects.runnerbuddy.controllers.gear.GearController;
import studio.rrprojects.runnerbuddy.gui.popups.EditGearPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GearCard {
    private GearController gearController;
    private CharacterContainer character;
    private JComboBox<String> boxSubCategory;
    private JButton buttonClear;
    private JPanel panelMain;
    private JList<String> listAvailable;
    private JList<String> listSelected;
    private JLabel textStarting;
    private JLabel textRemaining;
    private JComboBox<String> boxCategory;
    private LinkedHashMap<String, DefaultListModel<String>> listModelArray;
    private LinkedHashMap<String, DefaultComboBoxModel<String>> subcategoryList;
    String selectedCategory, selectedSubcategory;

    public GearCard(CharacterContainer characterContainer) {
        character = characterContainer;
        selectedCategory = "";
        selectedSubcategory = "";

        PopulateCategoryBox();
        PopulateSubcategoryBox();
        PopulateAvailableList();

        UpdateAll();

        boxSubCategory.addActionListener(actionEvent -> UpdateAll());
        boxCategory.addActionListener(actionEvent -> UpdateAll());
        listAvailable.addMouseListener(new MouseAdapter() {
        });
        listAvailable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(e.getPoint());
                    GearSelect(list.getSelectedValue().toString());
                }
            }
        });

        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                character.getGearController().ClearGearList();
                UpdateAll();
            }
        });
    }

    private void GearSelect(String searchTerm) {
        if (character.getGearController().SearchForGear(searchTerm, selectedSubcategory) != null) {
            new EditGearPopup(character.getGearController().SearchForGear(searchTerm, selectedSubcategory), character, this);
        }
    }

    private void PopulateCategoryBox() {
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();

        for (Map.Entry<String, ArrayList<String>> category : character.getGearController().getCategoryList().entrySet()) {
            boxModel.addElement(category.getKey());
        }

        boxCategory.setModel(boxModel);
    }

    private void PopulateSubcategoryBox() {
        String searchTerm = Objects.requireNonNull(boxCategory.getSelectedItem()).toString();

        ArrayList<String> list = character.getGearController().getCategoryList().get(searchTerm);

        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();

        for (String subcategory : list) {
            boxModel.addElement(subcategory);
        }

        boxSubCategory.setModel(boxModel);
    }

    private void PopulateAvailableList() {
        boolean showAll = false;
        String searchTerm = Objects.requireNonNull(boxSubCategory.getSelectedItem()).toString();
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (GearContainer gear : character.getGearController().getSubcategoryList().get(searchTerm)) {
            if (showAll || gear.getAvailabilityRating() <= 8) {
                listModel.addElement(gear.getItemName());
            }
        }

        listAvailable.setModel(listModel);
    }

    private void PopulateSelectedList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (Map.Entry<String, GearContainer> gear : character.getGearController().getSelectedGearList().entrySet()) {
            listModel.addElement(gear.getKey());
        }

        listSelected.setModel(listModel);
    }

    public void UpdateAll() {
        textStarting.setText("Starting Nuyen: " + character.getResourceController().getBaseResources() + "¥");

        if (!selectedCategory.equalsIgnoreCase(Objects.requireNonNull(boxCategory.getSelectedItem()).toString())) {
            PopulateSubcategoryBox();
            selectedCategory = boxCategory.getSelectedItem().toString();
        }

        if (!selectedSubcategory.equalsIgnoreCase(Objects.requireNonNull(boxSubCategory.getSelectedItem()).toString())) {
            PopulateAvailableList();
            selectedSubcategory = boxSubCategory.getSelectedItem().toString();
        }

        PopulateSelectedList();

        textRemaining.setText("Remaining Nuyen: " + character.getResourceController().getRemainingResources() + "¥");
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
        panelMain.setLayout(new GridLayoutManager(2, 6, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBackground(new Color(-14079180));
        panelMain.setForeground(new Color(-11805347));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-14079180));
        scrollPane1.setForeground(new Color(-11805347));
        panelMain.add(scrollPane1, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        listAvailable = new JList();
        listAvailable.setBackground(new Color(-14079180));
        listAvailable.setForeground(new Color(-11805347));
        scrollPane1.setViewportView(listAvailable);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setBackground(new Color(-14079180));
        scrollPane2.setForeground(new Color(-11805347));
        panelMain.add(scrollPane2, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        listSelected = new JList();
        listSelected.setBackground(new Color(-14079180));
        listSelected.setForeground(new Color(-11805347));
        scrollPane2.setViewportView(listSelected);
        boxSubCategory = new JComboBox();
        boxSubCategory.setBackground(new Color(-14079180));
        boxSubCategory.setForeground(new Color(-11805347));
        panelMain.add(boxSubCategory, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonClear = new JButton();
        buttonClear.setBackground(new Color(-14079180));
        buttonClear.setForeground(new Color(-11805347));
        buttonClear.setText("Clear All");
        panelMain.add(buttonClear, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textStarting = new JLabel();
        textStarting.setForeground(new Color(-11805347));
        textStarting.setText("Starting Nuyen: X");
        panelMain.add(textStarting, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textRemaining = new JLabel();
        textRemaining.setForeground(new Color(-11805347));
        textRemaining.setText("Remaining Nuyen: Y");
        panelMain.add(textRemaining, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        separator1.setOrientation(1);
        panelMain.add(separator1, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        separator2.setOrientation(1);
        panelMain.add(separator2, new GridConstraints(0, 4, 2, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        boxCategory = new JComboBox();
        boxCategory.setBackground(new Color(-14079180));
        boxCategory.setForeground(new Color(-11805347));
        panelMain.add(boxCategory, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
