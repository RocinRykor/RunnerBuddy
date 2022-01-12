package studio.rrprojects.runnerbuddy.gui.popups.skills;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.constants.AttributeConstants;
import studio.rrprojects.runnerbuddy.containers.SkillMap;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SelectedSkillContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SpecializationContainer;
import studio.rrprojects.runnerbuddy.controllers.SkillsController;
import studio.rrprojects.runnerbuddy.gui.cards.skills.SkillsCard;
import studio.rrprojects.runnerbuddy.utils.JUtils;
import studio.rrprojects.runnerbuddy.utils.MiscUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class SelectSkillPopup {
    private final JFrame frame;
    private final CharacterContainer characterContainer;
    private final SkillsCard skillsCard;
    private JPanel panelMain;
    private JTree treeSkills;
    private JSlider sliderPoints;
    private JCheckBox checkBoxSpecialization;
    private JComboBox<String> comboBoxSpecialization;
    private JTextArea textDescription;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel panelSkills;
    private JPanel panelInformation;
    private JCheckBox checkboxBuildRepair;
    private JLabel labelDisplayString;
    private JButton submitCloseButton;
    private JButton buttonCustomSkill;
    private LinkedHashMap<String, SkillMap> skillMap;
    private SkillContainer selectedSkill;
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

        treeSkills.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        treeSkills.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = treeSkills.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = treeSkills.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    assert selPath != null;
                    DoubleClickEvent(selPath);
                }
            }
        });
        checkBoxSpecialization.addActionListener(actionEvent -> updateDisplayString());
        checkboxBuildRepair.addActionListener(actionEvent -> updateDisplayString());
        sliderPoints.addChangeListener(changeEvent -> {
            checkSpecialization();
            updateDisplayString();
        });
        comboBoxSpecialization.addActionListener(actionEvent -> updateDisplayString());
        cancelButton.addActionListener(actionEvent -> frame.dispose());
        submitButton.addActionListener(actionEvent -> SubmitEvent());
        submitCloseButton.addActionListener(actionEvent -> {
            SubmitEvent();
            frame.dispose();
        });
        buttonCustomSkill.addActionListener(actionEvent -> CustomSkillEvent());
        panelMain.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                System.out.println("KEY PRESSED: " + e);
            }
        });
    }

    private void CustomSkillEvent() {
        new CustomSkillPopup(this);
    }

    private void SubmitEvent() {
        //Runs when the skill is being added to the player list
        baseValue = sliderPoints.getValue();
        selectedSkill.setBaseLevel(baseValue);

        //Handle Specializations
        if (checkBoxSpecialization.isSelected()) {
            selectedSkill.setActualLevel(baseValue - 1);

            String specializationName = Objects.requireNonNull(comboBoxSpecialization.getSelectedItem()).toString();
            int specializationLevel = baseValue + 1;

            SpecializationContainer specializationContainer = new SpecializationContainer(specializationName, specializationLevel);
            selectedSkill.addSpecialization(specializationContainer);
        } else {
            selectedSkill.getSelectedSpecializations().clear();
            selectedSkill.setActualLevel(baseValue);
        }

        //Check to see if the selectedSkill is already in the SkillsController list
        SkillsController skillsController = characterContainer.getSkillsController();

        if (skillsController.containsSkill(selectedSkill)) {
            SelectedSkillContainer tmp = skillsController.getSkill(selectedSkill);
            skillsController.removeSkill(tmp);
        }

        SelectedSkillContainer selectedSkillContainer = new SelectedSkillContainer(selectedSkill);
        skillsController.getSelectedSkillList().add(selectedSkillContainer);

        skillsCard.Update();
    }

    private void checkSpecialization() {
        int value = sliderPoints.getValue();

        if (value > 1) {
            checkBoxSpecialization.setEnabled(true);
            checkBoxSpecialization.setToolTipText("Select this to specialize your skill");
        } else {
            checkBoxSpecialization.setSelected(false);
            checkBoxSpecialization.setEnabled(false);
            checkBoxSpecialization.setToolTipText("You must have at least 2 points allotted to a skill before you can specialize it");
        }

    }

    private void DoubleClickEvent(TreePath selPath) {
        DefaultMutableTreeNode finalNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
        Object selectedObject = finalNode.getUserObject();

        if (selectedObject instanceof SkillContainer) {
            SkillContainer skillContainer = (SkillContainer) selectedObject;
            ProcessSkillContainer(skillContainer);
        }

    }

    private void ProcessSkillContainer(SkillContainer skillContainer) {
        selectedSkill = skillContainer;

        UpdateDescription();

        updateBuildRepair();

        //Reset Specialization
        checkBoxSpecialization.setSelected(false);

        ResetSlider();

        ArrayList<String> availableSpecializations = selectedSkill.getAvailableSpecializations();
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addAll(availableSpecializations);
        comboBoxSpecialization.setModel(boxModel);
        comboBoxSpecialization.setSelectedIndex(0);

        updateDisplayString();
    }

    private void ResetSlider() {
        sliderPoints.setValue(3);
    }

    private void updateBuildRepair() {
        boolean buildRepair = selectedSkill.isBuildRepairAvailable();

        checkboxBuildRepair.setVisible(buildRepair);

        checkboxBuildRepair.setSelected(false);
    }

    private void updateDisplayString() {
        String displayString = "";

        boolean buildRepair = checkboxBuildRepair.isSelected();
        boolean isSpecialized = checkBoxSpecialization.isSelected();

        if (buildRepair) {
            selectedSkill.setSkillName("(B/R) " + selectedSkill.getSkillBaseName());
            selectedSkill.setLinkedAttribute(AttributeConstants.INTELLIGENCE);
        } else {
            selectedSkill.setSkillName(selectedSkill.getSkillBaseName());
            selectedSkill.setLinkedAttribute(selectedSkill.getBaseAttribute());
        }

        UpdateDescription();

        baseValue = sliderPoints.getValue();

        int attributeScore = 1;

        try {
            attributeScore = characterContainer.getAttributeController().getAttributeMap().get(selectedSkill.getLinkedAttribute()).getTotalPoints();
        } catch (NullPointerException ignored) {
        }


        int totalCost = calculateSkillCost(baseValue, attributeScore);

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

    private void UpdateDescription() {
        textDescription.setText(selectedSkill.getOverview());
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
        panelSkills.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelSkills, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(300, -1), null, 1, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelSkills.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        treeSkills = new JTree();
        treeSkills.putClientProperty("JTree.lineStyle", "");
        treeSkills.putClientProperty("html.disable", Boolean.FALSE);
        scrollPane1.setViewportView(treeSkills);
        buttonCustomSkill = new JButton();
        buttonCustomSkill.setText("Create Custom Skill");
        panelSkills.add(buttonCustomSkill, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelInformation = new JPanel();
        panelInformation.setLayout(new GridLayoutManager(7, 5, new Insets(5, 5, 5, 5), -1, -1));
        panelMain.add(panelInformation, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sliderPoints = new JSlider();
        sliderPoints.setMajorTickSpacing(1);
        sliderPoints.setMaximum(6);
        sliderPoints.setMinimum(1);
        sliderPoints.setPaintLabels(true);
        sliderPoints.setPaintTicks(true);
        sliderPoints.setSnapToTicks(true);
        sliderPoints.setValue(3);
        panelInformation.add(sliderPoints, new GridConstraints(4, 0, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submitButton = new JButton();
        submitButton.setText("Submit");
        panelInformation.add(submitButton, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelDisplayString = new JLabel();
        labelDisplayString.setText("Choose a Skill");
        panelInformation.add(labelDisplayString, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        checkboxBuildRepair = new JCheckBox();
        checkboxBuildRepair.setText("Build/Repair");
        panelInformation.add(checkboxBuildRepair, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submitCloseButton = new JButton();
        submitCloseButton.setText("Submit and Close");
        panelInformation.add(submitCloseButton, new GridConstraints(6, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panelInformation.add(cancelButton, new GridConstraints(6, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textDescription = new JTextArea();
        textDescription.setEditable(false);
        textDescription.setLineWrap(true);
        textDescription.setWrapStyleWord(true);
        panelInformation.add(textDescription, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(100, 50), null, 0, false));
        checkBoxSpecialization = new JCheckBox();
        checkBoxSpecialization.setText("Specialization");
        panelInformation.add(checkBoxSpecialization, new GridConstraints(3, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBoxSpecialization = new JComboBox();
        comboBoxSpecialization.setEditable(true);
        panelInformation.add(comboBoxSpecialization, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
