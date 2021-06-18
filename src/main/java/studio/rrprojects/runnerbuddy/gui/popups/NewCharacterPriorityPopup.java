package studio.rrprojects.runnerbuddy.gui.popups;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.PriorityController;
import studio.rrprojects.runnerbuddy.gui.CharacterCreationWindow;
import studio.rrprojects.runnerbuddy.gui.LaunchWindow;
import studio.rrprojects.runnerbuddy.misc.PriorityObject;
import studio.rrprojects.runnerbuddy.misc.PriorityOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class NewCharacterPriorityPopup extends JFrame {
    private JPanel panelMain;
    private JLabel labelHeader;
    private JPanel panelPrimary;
    private JList<String> listCategory;
    private JList<String> listSelectedValue;
    private JButton buttonUp;
    private JButton buttonDown;
    private JList<String> listPriorityLevel;
    private JButton submitButton;
    private JButton buttonCancel;
    private CharacterContainer characterContainer;
    private LinkedHashMap<String, PriorityOptions> priorityMasterList;
    private PriorityOptions priorityOptions;

    private final String upString = "Move Up";
    private final String downString = "Move Down";
    private DefaultListModel<String> modelCategory;

    private final String confirmationString = "Are you sure?\n" +
            "\n" +
            "Character priorities can not be changed after this point.";
    private final String confirmationTitleString = "Confirm Priority Choice...";

    public NewCharacterPriorityPopup(String title, CharacterContainer characterContainer) {
        super(title);
        this.characterContainer = characterContainer;

        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setSize(600, 400);
        setVisible(true);

        //Load the JSON Information to the lists
        CreatePriorityTable();
        PopulateCategoryList();
        PopulateSelectedValueList();

        //Formats the Lists
        FormatList(listCategory, SwingConstants.CENTER);
        FormatList(listSelectedValue, SwingConstants.CENTER);
        FormatList(listPriorityLevel, SwingConstants.CENTER);

        //Setup Buttons
        ClassLoader cl = this.getClass().getClassLoader();

        buttonUp.setEnabled(false);
        buttonUp.setActionCommand(upString);
        buttonUp.setText("");
        Icon upIcon = loadAndResize(cl.getResource("IMG/arrow_up.png"));
        buttonUp.setIcon(upIcon);
        buttonUp.setHideActionText(true);

        buttonDown.setEnabled(false);
        buttonDown.setActionCommand(downString);
        buttonDown.setText("");
        Icon downIcon = loadAndResize(cl.getResource("IMG/arrow_down.png"));
        buttonDown.setIcon(downIcon);
        buttonDown.setHideActionText(true);

        buttonUp.addActionListener(new UpDownListener());

        buttonDown.addActionListener(new UpDownListener());

        listCategory.addListSelectionListener(listSelectionEvent -> {
            UpdateButtons();
        });
        listSelectedValue.addListSelectionListener(listSelectionEvent -> {
            listSelectedValue.clearSelection();
        });
        submitButton.addActionListener(actionEvent -> {
            if (ConfirmChoice() == 0) {
                //Save the current map to the PriorityController
                LinkedHashMap<String, PriorityObject> priorityMap = savePriorityMap();
                characterContainer.ProcessPriorityMap(priorityMap);

                new CharacterCreationWindow(characterContainer);

                this.dispose();
            }
        });
        buttonCancel.addActionListener(actionEvent -> {
            LaunchWindow launch = new LaunchWindow();
            this.dispose();
        });
    }

    private Icon loadAndResize(URL resource) {
        ImageIcon imageIcon = new ImageIcon(resource); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg);  // transform it back
    }

    private LinkedHashMap<String, PriorityObject> savePriorityMap() {
        LinkedHashMap<String, PriorityObject> temp = new LinkedHashMap<>();

        PriorityController priorityController = characterContainer.getPriorityController();

        for (int i = 0; i < modelCategory.getSize(); i++) {
            String priorityLevel = getCharForNumber(i);

            String category = modelCategory.getElementAt(i);

            assert priorityLevel != null;
            PriorityOptions priortyOption = priorityController.getOptionsByLevel(priorityLevel);

            PriorityObject priorityObject = priortyOption.get(category);

            temp.put(category, priorityObject);
        }

        return temp;
    }

    private int ConfirmChoice() {
        int input = JOptionPane.showConfirmDialog(null, confirmationString, confirmationTitleString,
                JOptionPane.OK_CANCEL_OPTION);

        System.out.println(input);

        return input;
    }

    private void UpdateButtons() {
        int index = listCategory.getSelectedIndex();
        int maxIndex = listCategory.getModel().getSize() - 1;

        buttonUp.setEnabled(true);
        buttonDown.setEnabled(true);

        if (index <= 0) {
            buttonUp.setEnabled(false);
        }

        if (index >= maxIndex) {
            buttonDown.setEnabled(false);
        }
    }

    private void PopulateSelectedValueList() {
        DefaultListModel<String> model = new DefaultListModel<>();

        PriorityController priorityController = characterContainer.getPriorityController();

        for (int i = 0; i < modelCategory.getSize(); i++) {
            String priorityLevel = getCharForNumber(i);

            String category = modelCategory.getElementAt(i);

            assert priorityLevel != null;
            PriorityOptions priortyOption = priorityController.getOptionsByLevel(priorityLevel);

            PriorityObject priorityObject = priortyOption.get(category);
            String display = priorityObject.getPriorityCategory() + ": " + priorityObject.getDisplayString();
            model.addElement(display);
        }

        listSelectedValue.setModel(model);
    }

    private String getCharForNumber(int i) {
        return i > -1 && i < 26 ? String.valueOf((char) (i + 65)) : null;
    }

    private void FormatList(JList<String> list, int swingAlignment) {
        int listHeight = (int) list.getSize().getHeight();
        int listSize = list.getModel().getSize();

        int evenSpacing = (listHeight / listSize);
        list.setFixedCellHeight(evenSpacing);

        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(swingAlignment);
    }

    private void PopulateCategoryList() {
        modelCategory = new DefaultListModel<>();
        String[] priorityCategoryList = characterContainer.getPriorityController().getListCategory();

        for (String category : priorityCategoryList) {
            modelCategory.addElement(category);
        }

        listCategory.setModel(modelCategory);
    }

    private void CreatePriorityTable() {
        PriorityController priorityController = characterContainer.getPriorityController();
        String[] priorityLevelList = {"a", "b", "c", "d", "e"};
        priorityMasterList = new LinkedHashMap<>();

        for (String priorityLevel : priorityLevelList) {
            priorityOptions = priorityController.getOptionsByLevel(priorityLevel);
            priorityMasterList.put(priorityLevel, priorityOptions);
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
        panelMain.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), -1, -1));
        panelMain.setMinimumSize(new Dimension(-1, -1));
        panelMain.setPreferredSize(new Dimension(-1, -1));
        labelHeader = new JLabel();
        labelHeader.setText("Shadowrun 3e Character Creation: Priority");
        panelMain.add(labelHeader, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelPrimary = new JPanel();
        panelPrimary.setLayout(new GridLayoutManager(8, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelPrimary, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Final Results:");
        panelPrimary.add(label1, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panelPrimary.add(buttonCancel, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submitButton = new JButton();
        submitButton.setText("Submit");
        panelPrimary.add(submitButton, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Category (Select One)");
        panelPrimary.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listPriorityLevel = new JList();
        listPriorityLevel.setEnabled(false);
        listPriorityLevel.setLayoutOrientation(0);
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        defaultListModel1.addElement("Priorty A");
        defaultListModel1.addElement("Priorty B");
        defaultListModel1.addElement("Priorty C");
        defaultListModel1.addElement("Priorty D");
        defaultListModel1.addElement("Priorty E");
        listPriorityLevel.setModel(defaultListModel1);
        listPriorityLevel.setSelectionMode(0);
        listPriorityLevel.setVisibleRowCount(5);
        panelPrimary.add(listPriorityLevel, new GridConstraints(2, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        buttonUp = new JButton();
        buttonUp.setText("UP");
        panelPrimary.add(buttonUp, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonDown = new JButton();
        buttonDown.setText("DOWN");
        panelPrimary.add(buttonDown, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator1 = new JSeparator();
        panelPrimary.add(separator1, new GridConstraints(4, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        listSelectedValue = new JList();
        listSelectedValue.setEnabled(true);
        final DefaultListModel defaultListModel2 = new DefaultListModel();
        listSelectedValue.setModel(defaultListModel2);
        listSelectedValue.setSelectionMode(0);
        listSelectedValue.setVisibleRowCount(5);
        panelPrimary.add(listSelectedValue, new GridConstraints(6, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        listCategory = new JList();
        listCategory.setEnabled(true);
        listCategory.setLayoutOrientation(0);
        final DefaultListModel defaultListModel3 = new DefaultListModel();
        listCategory.setModel(defaultListModel3);
        listCategory.setSelectionMode(0);
        listCategory.setVisibleRowCount(5);
        panelPrimary.add(listCategory, new GridConstraints(2, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JSeparator separator2 = new JSeparator();
        panelPrimary.add(separator2, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Select a category and use the Up/Down buttons on the right to move and rank them.");
        panelMain.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private class UpDownListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //This method can be called only when
            //there's a valid selection,
            //so go ahead and move the list item.
            int moveMe = listCategory.getSelectedIndex();

            if (e.getActionCommand().equals(upString)) {
                //UP ARROW BUTTON
                if (moveMe != 0) {
                    //not already at top
                    swap(moveMe, moveMe - 1);
                    listCategory.setSelectedIndex(moveMe - 1);
                    listCategory.ensureIndexIsVisible(moveMe - 1);
                }
            } else {
                //DOWN ARROW BUTTON
                if (moveMe != listCategory.getModel().getSize() - 1) {
                    //not already at bottom
                    swap(moveMe, moveMe + 1);
                    listCategory.setSelectedIndex(moveMe + 1);
                    listCategory.ensureIndexIsVisible(moveMe + 1);
                }
            }
        }
    }

    //Swap two elements in the list.
    private void swap(int a, int b) {
        String aString = modelCategory.getElementAt(a);
        String bString = modelCategory.getElementAt(b);
        modelCategory.set(a, bString);
        modelCategory.set(b, aString);

        PopulateSelectedValueList();
    }
}
