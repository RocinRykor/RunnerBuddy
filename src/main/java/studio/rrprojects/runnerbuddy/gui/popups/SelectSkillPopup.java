package studio.rrprojects.runnerbuddy.gui.popups;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.containers.SkillContainer;
import studio.rrprojects.runnerbuddy.containers.SkillMap;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.SkillsCard;
import studio.rrprojects.runnerbuddy.utils.JUtils;
import studio.rrprojects.runnerbuddy.utils.MiscUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SelectSkillPopup {
    private final JFrame frame;
    private final CharacterContainer characterContainer;
    private final SkillsCard skillsCard;
    private JPanel panelMain;
    private JTree treeSkills;
    private JSlider sliderPoints;
    private JCheckBox checkBoxSpecialization;
    private JComboBox comboBoxSpecialization;
    private JTable tableDescription;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel panelSkills;
    private JPanel panelInformation;
    private JCheckBox checkboxBuildRepair;
    private JLabel labelDisplayString;
    private LinkedHashMap<String, SkillMap> skillMap;
    private SkillContainer selectedSkill;
    private DefaultTableModel discriptionTableModel;
    private int baseValue = 1;

    public SelectSkillPopup(CharacterContainer characterContainer, SkillsCard skillsCard) {
        this.characterContainer = characterContainer;
        this.skillsCard = skillsCard;
        frame = new JFrame();
        $$$setupUI$$$();
        JUtils.OpenFrameAtMouseLocation(frame);
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        frame.repaint();
        panelMain.repaint();

        FormatPanels();
        PopulateSkillTree();

        treeSkills.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = treeSkills.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = treeSkills.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    if (e.getClickCount() == 1) {
                        //mySingleClick(selRow, selPath);
                    } else if (e.getClickCount() == 2) {
                        DoubleClickEvent(selRow, selPath);
                    }
                }
            }
        });
        checkBoxSpecialization.addActionListener(actionEvent -> updateDisplayString());
        checkboxBuildRepair.addActionListener(actionEvent -> updateDisplayString());
        sliderPoints.addChangeListener(changeEvent -> {
            checkSpecialization();
            updateDisplayString();
        });
        comboBoxSpecialization.addActionListener(actionEvent -> {
            updateDisplayString();
        });
        cancelButton.addActionListener(actionEvent -> {
            frame.dispose();
        });
        submitButton.addActionListener(actionEvent -> {
            characterContainer.getSkillsController().addSkillFromPopup(this);
            skillsCard.Update();
            frame.dispose();
        });
    }

    private void checkSpecialization() {
        int value = sliderPoints.getValue();

        if (value > 1) {
            checkBoxSpecialization.setEnabled(true);
            checkBoxSpecialization.setToolTipText("Select this to specialize your skill");
        } else {
            checkBoxSpecialization.setSelected(false);
            checkBoxSpecialization.setEnabled(false);
            checkBoxSpecialization.setToolTipText("You must have at least 2 points alloted to a skill before you can specialize it");
        }

    }

    private void DoubleClickEvent(int selRow, TreePath selPath) {
        DefaultMutableTreeNode finalNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
        Object selectedObject = finalNode.getUserObject();

        if (selectedObject instanceof SkillContainer) {
            SkillContainer skillContainer = (SkillContainer) selectedObject;
            ProcessSkillContainer(skillContainer);
        }


    }

    private void ProcessSkillContainer(SkillContainer skillContainer) {
        selectedSkill = skillContainer;

        //skillContainer.setSkillLevel(baseValue);

        SetDescription();

        updateBuildRepair();

        ArrayList<String> availableSpecializations = selectedSkill.getAvailableSpecializations();
        DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
        boxModel.addAll(availableSpecializations);
        comboBoxSpecialization.setModel(boxModel);
        comboBoxSpecialization.setSelectedIndex(0);

        updateDisplayString();
    }

    private void updateBuildRepair() {
        //TODO - If Build repair is active attribute changes to Intelligence

        boolean buildRepair = selectedSkill.isBuildRepairAvailible();

        checkboxBuildRepair.setVisible(buildRepair);

        if (!buildRepair) {
            checkboxBuildRepair.setSelected(false);
        }
    }

    private void updateDisplayString() {
        String displayString = "";
        Boolean buildRepair = checkboxBuildRepair.isSelected();
        Boolean isSpecialized = checkBoxSpecialization.isSelected();
        baseValue = sliderPoints.getValue();
        int attributeScore = 1;

        try {
            attributeScore = characterContainer.getAttributeController().getAttributeMap().get(selectedSkill.getAttribute()).getTotalPoints();
        } catch (NullPointerException ignored) {
        }


        int totalCost = calculateSkillCost(baseValue, attributeScore);

        if (buildRepair) {
            displayString += "(B/R) ";
        }

        if (isSpecialized) {
            displayString += selectedSkill.getSkillName() + " (" + (baseValue - 1) + "), "
                    + comboBoxSpecialization.getSelectedItem() + " (" + (baseValue + 1) + ") ";
        } else {
            displayString += selectedSkill.getSkillName() + " (" + baseValue + ") ";
        }

        displayString += "| Total Points: " + totalCost;

        labelDisplayString.setText(displayString);
    }

    private int calculateSkillCost(int baseValue, int attributeScore) {
        if (baseValue <= attributeScore) {
            return baseValue;
        } else {
            int remainder = baseValue - attributeScore;
            return attributeScore + (remainder * 2);
        }

    }

    private void SetDescription() {
        System.out.println("SelectSkill Popup: Setting Description!");

        discriptionTableModel = selectedSkill.getTableDiscription();

        //create table with data
        tableDescription.setModel(discriptionTableModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setVerticalAlignment(JLabel.TOP);

        tableDescription.getColumn("1").setCellRenderer(centerRenderer);
        tableDescription.getColumn("2").setCellRenderer(new MyCellRenderer());
    }

    private void PopulateSkillTree() {
        //First Get the skill map
        skillMap = characterContainer.getSkillsController().getMasterSkillMap();

        //convert map to Tree model
        DefaultTreeModel treeModel = MiscUtils.convertMasterSkillMapToJTree(skillMap, "All Skills");
        //set tree to use new model

        treeSkills.setModel(treeModel);
    }

    private void FormatPanels() {
        panelSkills.setBorder(BorderFactory.createTitledBorder("Select A Skill"));
        panelInformation.setBorder(BorderFactory.createTitledBorder("Skill Information"));
    }

    public SkillContainer getSelectedSkill() {
        return selectedSkill;
    }

    public void setSelectedSkill(SkillContainer selectedSkill) {
        this.selectedSkill = selectedSkill;
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
        panelMain.setLayout(new GridLayoutManager(1, 2, new Insets(10, 10, 10, 10), -1, -1));
        panelMain.setMinimumSize(new Dimension(800, 600));
        panelMain.setPreferredSize(new Dimension(800, 600));
        panelSkills = new JPanel();
        panelSkills.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelSkills, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelSkills.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        treeSkills = new JTree();
        scrollPane1.setViewportView(treeSkills);
        panelInformation = new JPanel();
        panelInformation.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelInformation, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sliderPoints = new JSlider();
        sliderPoints.setMajorTickSpacing(1);
        sliderPoints.setMaximum(6);
        sliderPoints.setMinimum(1);
        sliderPoints.setPaintLabels(true);
        sliderPoints.setPaintTicks(true);
        sliderPoints.setSnapToTicks(true);
        sliderPoints.setValue(3);
        panelInformation.add(sliderPoints, new GridConstraints(3, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkBoxSpecialization = new JCheckBox();
        checkBoxSpecialization.setText("Specialization");
        panelInformation.add(checkBoxSpecialization, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxSpecialization = new JComboBox();
        comboBoxSpecialization.setEditable(true);
        panelInformation.add(comboBoxSpecialization, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tableDescription = new JTable();
        tableDescription.setShowHorizontalLines(false);
        tableDescription.setShowVerticalLines(false);
        panelInformation.add(tableDescription, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        submitButton = new JButton();
        submitButton.setText("Submit");
        panelInformation.add(submitButton, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panelInformation.add(cancelButton, new GridConstraints(4, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelDisplayString = new JLabel();
        labelDisplayString.setText("Choose a Skill");
        panelInformation.add(labelDisplayString, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkboxBuildRepair = new JCheckBox();
        checkboxBuildRepair.setText("Build/Repair");
        panelInformation.add(checkboxBuildRepair, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private class MyCellRenderer extends JTextArea implements TableCellRenderer {
        MyCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }
}
