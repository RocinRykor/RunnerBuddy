package studio.rrprojects.runnerbuddy.gui.cards.skills;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.constants.SkillConstants;
import studio.rrprojects.runnerbuddy.containers.skills.SelectedSkillContainer;
import studio.rrprojects.runnerbuddy.gui.cards.Card;
import studio.rrprojects.runnerbuddy.gui.cards.components.SmallProgressBar;
import studio.rrprojects.runnerbuddy.gui.popups.SelectSkillPopup;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class SkillsCard extends Card {
    private JPanel panelMain;
    private JButton addSkillButton;
    private JTable tableDescription;
    private JPanel panelSkills;
    private JPanel panelInformation;
    private JPanel panelProgressBars;
    private JPanel panelTable;
    private SmallProgressBar progressBarActive;
    private SmallProgressBar progressBarKnowledge;
    private SmallProgressBar progressBarLanguage;
    private JTree treeSkills;

    public SkillsCard(String title) {
        super(title);
        setPanel(panelMain);
    }

    @Override
    public void Initialize() {
        super.Initialize();
        populateInformationPanel();

        treeSkills.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        addSkillButton.addActionListener(actionEvent -> AddNewSkill());

        treeSkills.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = treeSkills.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = treeSkills.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    if (e.getClickCount() == 2) {
                        DoubleClickEvent(selRow, selPath);
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        RightClickEvent(selRow, selPath, e);
                    }

                }
            }
        });
    }

    private void DoubleClickEvent(int selRow, TreePath selPath) {
        DefaultMutableTreeNode finalNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
        Object selectedObject = finalNode.getUserObject();

        if (selectedObject instanceof SelectedSkillContainer) {
            SelectedSkillContainer skillContainer = (SelectedSkillContainer) selectedObject;
            EditSkill(skillContainer);
        }
    }

    private void RightClickEvent(int selRow, TreePath selPath, MouseEvent e) {
        DefaultMutableTreeNode finalNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
        Object selectedObject = finalNode.getUserObject();

        if (selectedObject instanceof SelectedSkillContainer) {
            SelectedSkillContainer skillContainer = (SelectedSkillContainer) selectedObject;
            RightClickPopup(skillContainer, e.getX(), e.getY());
        }
    }

    private void RightClickPopup(SelectedSkillContainer skillContainer, int x, int y) {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem editOption = new JMenuItem("EDIT");
        JMenuItem deleteOption = new JMenuItem("REMOVE");
        JSeparator separator = new JPopupMenu.Separator();
        JMenuItem cancelOption = new JMenuItem("CANCEL");

        popup.add(editOption);
        popup.add(deleteOption);
        popup.add(separator);
        popup.add(cancelOption);

        editOption.setEnabled(false);
        editOption.setToolTipText("Not Yet Implemented");

        deleteOption.addActionListener(actionEvent -> DeleteSkill(skillContainer));

        popup.show(panelMain, x, y);
    }

    private void DeleteSkill(SelectedSkillContainer skillContainer) {
        getCharacterContainer().getSkillsController().getSelectedSkillList().remove(skillContainer);
        Update();
    }

    private void EditSkill(SelectedSkillContainer skillContainer) {
        // TODO: 8/6/21 ADD EDIT SKILL
    }

    private void CalculateSkillPoints() {
        ArrayList<SelectedSkillContainer> skillList = getCharacterContainer().getSkillsController().getSelectedSkillList();

        HashMap<String, Integer> spentPointMap = NewSpentPointMap();


        for (SelectedSkillContainer skill : skillList) {
            skill.CaculatePointCost(getCharacterContainer());

            String skillType = skill.getSkillType();
            int pointsSpent = skill.getPointCost();

            int oldValue = spentPointMap.get(skillType);

            int newValue = pointsSpent + oldValue;

            spentPointMap.replace(skillType, newValue);
        }

        //Update the bars
        progressBarActive.setValue(spentPointMap.get(SkillConstants.ACTIVE));
        progressBarKnowledge.setValue(spentPointMap.get(SkillConstants.KNOWLEDGE));
        progressBarLanguage.setValue(spentPointMap.get(SkillConstants.LANGUAGE));


    }

    private HashMap<String, Integer> NewSpentPointMap() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(SkillConstants.ACTIVE, 0);
        hashMap.put(SkillConstants.KNOWLEDGE, 0);
        hashMap.put(SkillConstants.LANGUAGE, 0);
        return hashMap;
    }

    private void AddNewSkill() {
        new SelectSkillPopup(getCharacterContainer(), this);
    }

    private void populateInformationPanel() {
        //panelInformation.setLayout(new GridLayout(0, 1));
        int skillPoints = getCharacterContainer().getSkillsController().getMaxActiveSkillPoints();
        int intelligence = 1;

        try {
            intelligence = getCharacterContainer().getAttributeController().getAttributeMap().get("Intelligence").getTotalPoints();
        } catch (NullPointerException e) {
            System.out.println("SkillsCard Error: " + e);
        }

        progressBarActive.setTitle("Active Skills");
        progressBarActive.setMax(skillPoints);
        progressBarActive.UpdateValueString();

        progressBarKnowledge.setTitle("Knowledge Skills");
        progressBarKnowledge.setMax(intelligence * 5);
        progressBarKnowledge.UpdateValueString();

        progressBarLanguage.setTitle("Language Skills");
        progressBarLanguage.setMax((int) Math.floor(intelligence * 1.5));
        progressBarLanguage.UpdateValueString();

    }

    @Override
    public void Update() {
        super.Update();
        updateSkillsPanel();
        populateInformationPanel();
        CalculateSkillPoints();
        expandTree(treeSkills);
    }

    private void expandTree(JTree tree) {
        int j = tree.getRowCount();
        int i = 0;
        while (i < j) {
            tree.expandRow(i);
            i += 1;
            j = tree.getRowCount();
        }
    }

    private void updateSkillsPanel() {
        DefaultTreeModel treeModel = getCharacterContainer().getSkillsController().getSelectedSkillTree();

        treeSkills.setModel(treeModel);
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
        panelMain.setLayout(new GridLayoutManager(4, 2, new Insets(10, 10, 10, 10), -1, -1));
        panelSkills = new JPanel();
        panelSkills.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelSkills, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelSkills.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        treeSkills = new JTree();
        scrollPane1.setViewportView(treeSkills);
        panelInformation = new JPanel();
        panelInformation.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelInformation, new GridConstraints(0, 1, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panelProgressBars = new JPanel();
        panelProgressBars.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelInformation.add(panelProgressBars, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        progressBarActive = new SmallProgressBar();
        panelProgressBars.add(progressBarActive.$$$getRootComponent$$$(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(30, 10), null, 0, false));
        progressBarKnowledge = new SmallProgressBar();
        panelProgressBars.add(progressBarKnowledge.$$$getRootComponent$$$(), new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(30, 10), null, 0, false));
        progressBarLanguage = new SmallProgressBar();
        panelProgressBars.add(progressBarLanguage.$$$getRootComponent$$$(), new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(30, 10), null, 0, false));
        panelTable = new JPanel();
        panelTable.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelInformation.add(panelTable, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tableDescription = new JTable();
        panelTable.add(tableDescription, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        addSkillButton = new JButton();
        addSkillButton.setText("Add Skill");
        panelInformation.add(addSkillButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
