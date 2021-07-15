package studio.rrprojects.runnerbuddy.gui.cards;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.containers.skills.SelectedSkillContainer;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.components.SmallProgressBar;
import studio.rrprojects.runnerbuddy.gui.popups.SelectSkillPopup;
import studio.rrprojects.runnerbuddy.Constants.SkillConstants;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SkillsCard extends Card {
    private final CharacterContainer characterContainer;
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
    private JButton recalculateButton;
    private ArrayList<SelectedSkillContainer> selectedSkillList;

    public SkillsCard(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        setPanel(panelMain);

        formatPanels();
        populateInformationPanel();
        addSkillButton.addActionListener(actionEvent -> AddNewSkill());
        recalculateButton.addActionListener(actionEvent -> CalculateSkillPoints());
    }

    private void CalculateSkillPoints() {
        ArrayList<SelectedSkillContainer> skillList = characterContainer.getSkillsController().getSelectedSkillList();

        HashMap<String, Integer> spentPointMap = NewSpentPointMap();


        for (SelectedSkillContainer skill : skillList) {
            skill.CaculatePointCost(characterContainer);

            String skillType = skill.getSkillType();
            int pointsSpent = skill.getPointCost();

            System.out.println("SkillsCard: " + skillType);

            int oldValue = spentPointMap.get(skillType);

            int newValue = pointsSpent + oldValue;

            System.out.println("SkillsCard: " + newValue);

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
        new SelectSkillPopup(characterContainer, this);
    }

    private void populateInformationPanel() {
        //panelInformation.setLayout(new GridLayout(0, 1));
        int skillPoints = characterContainer.getSkillsController().getSelectedPriority().getValueInt();
        int intelligence = 1;

        try {
            intelligence = characterContainer.getAttributeController().getAttributeMap().get("Intelligence").getTotalPoints();
        } catch (NullPointerException e) {
            System.out.println("SkillsCard Error: " + e);
        }

        progressBarActive.setTitle("Active Skills");
        progressBarActive.setMax(skillPoints);
        progressBarActive.UpdateValueString();
        //.add(activeSkillsProgress);

        //knowledgeSkillsProgress = new SmallProgressBar();
        progressBarKnowledge.setTitle("Knowledge Skills");
        progressBarKnowledge.setMax(intelligence * 5);
        progressBarKnowledge.UpdateValueString();
        //panelInformation.add(knowledgeSkillsProgress);

        //languageSkillsProgress = new SmallProgressBar();
        progressBarLanguage.setTitle("Language Skills");
        progressBarLanguage.setMax((int) Math.floor(intelligence * 1.5));
        progressBarLanguage.UpdateValueString();
        //panelInformation.add(languageSkillsProgress);
    }

    private void formatPanels() {
        //panelAvailiable.setBorder(BorderFactory.createTitledBorder("Select A Skill - Double Click to Add"));
        //panelSelected.setBorder(BorderFactory.createTitledBorder("Selected Skills - Double Click to Edit"));
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
        selectedSkillList = characterContainer.getSkillsController().getSelectedSkillList();
        DefaultTreeModel treeModel = convertSkillListToTreeNode();
        treeSkills.setModel(treeModel);
    }

    private DefaultTreeModel convertSkillListToTreeNode() {
        LinkedHashMap<String, ArrayList<SelectedSkillContainer>> skillMap = new LinkedHashMap<>();

        //TODO - Make tree like the Add Skill Tree -> Active, Knowledge, and Language in that order and subcategories


        for (SelectedSkillContainer skill : selectedSkillList) {
            String skillType = skill.getSkillType();
            String skillCategory = skill.getCategory();

            if (!skillMap.containsKey(skillType)) {
                skillMap.put(skillType, new ArrayList<SelectedSkillContainer>());
            }
            skillMap.get(skillType).add(skill);
        }

        DefaultMutableTreeNode masterNode = new DefaultMutableTreeNode("Selected Skills");

        for (String skillCategory : skillMap.keySet()) {
            DefaultMutableTreeNode categoryNode = new DefaultMutableTreeNode(skillCategory);

            for (SelectedSkillContainer skill : skillMap.get(skillCategory)) {
                DefaultMutableTreeNode skillNode = new DefaultMutableTreeNode(skill);
                categoryNode.add(skillNode);
            }

            masterNode.add(categoryNode);
        }

        return new DefaultTreeModel(masterNode);
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
        panelProgressBars.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelInformation.add(panelProgressBars, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        progressBarActive = new SmallProgressBar();
        panelProgressBars.add(progressBarActive.$$$getRootComponent$$$(), new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(30, 10), null, 0, false));
        progressBarKnowledge = new SmallProgressBar();
        panelProgressBars.add(progressBarKnowledge.$$$getRootComponent$$$(), new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(30, 10), null, 0, false));
        progressBarLanguage = new SmallProgressBar();
        panelProgressBars.add(progressBarLanguage.$$$getRootComponent$$$(), new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(30, 10), null, 0, false));
        recalculateButton = new JButton();
        recalculateButton.setText("Recalculate");
        panelProgressBars.add(recalculateButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
