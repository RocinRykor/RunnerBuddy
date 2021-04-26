package studio.rrprojects.runnerbuddy.gui.cards;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.controllers.RunnerBuilderController;
import studio.rrprojects.runnerbuddy.gui.popups.SelectSkillPopup;
import studio.rrprojects.runnerbuddy.utils.ColorUtils;
import studio.rrprojects.runnerbuddy.utils.FontUtils;
import studio.rrprojects.runnerbuddy.utils.JUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Skills extends Card {
    private JPanel panelMain;
    private JTree treeSkillList;
    private JTree treeSelectedSkills;
    private JButton addSelectedSkillButton;
    private JButton createCustomSkillButton;
    private JButton clearButton;
    private JTextArea textAreaSkills;

    public Skills(RunnerBuilderController controller, CharacterContainer characterContainer) {
        this.controller = controller;
        this.characterContainer = characterContainer;


        $$$setupUI$$$();
        SetColorsAndFonts();
        FormatTree(treeSkillList);
        FormatTree(treeSelectedSkills);
        PopulateSkillList();

        textAreaSkills.setText("Active Skills: 27/27 \n" +
                "Knowledge Skills 15/15\n" +
                "Language Skills 4/4");

        addSelectedSkillButton.addActionListener(actionEvent -> {
            SelectASkillButton();
        });
    }

    private void SelectASkillButton() {
        TreePath path = treeSkillList.getSelectionPath();
        SkillContainer skillContainer = null;

        if (path.getPathCount() >= 4) {
            //skillContainer = characterContainer.getSkillsController().searchSkillFromPath(path);
        } else {
            return;
        }

        if (skillContainer != null) {
           new SelectSkillPopup(skillContainer, this);
        }
    }

    private void PopulateSkillList() {
        DefaultTreeModel model = (DefaultTreeModel) treeSkillList.getModel();
        model.setRoot(new DefaultMutableTreeNode("Skills"));
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        //root.add(convertMapToTreeNode(characterContainer.getSkillsController().getMapActiveSkills(), "Active Skills"));
        //root.add(convertMapToTreeNode(characterContainer.getSkillsController().getMapKnowledgeSkills(), "Knowledge Skills"));
        //root.add(convertMapToTreeNode(characterContainer.getSkillsController().getMapLanguageSkills(), "Language Skills"));

        model.reload(root);
    }

    private MutableTreeNode convertMapToTreeNode(Map<String, JSONObject> map, String nodeName) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodeName);
        LinkedHashMap<String, DefaultMutableTreeNode> mapCategories = new LinkedHashMap<>();

        map.forEach((key, value) -> {
            String category = TextUtils.titleCase(value.getString("category"));
            if (!mapCategories.containsKey(category)) {
                mapCategories.put(category, new DefaultMutableTreeNode(category));
            }

            mapCategories.get(category).add(new DefaultMutableTreeNode(TextUtils.titleCase(key)));
        });

        mapCategories.forEach((key, value) -> {
            node.add(value);
        });

        return node;
    }

    private void FormatTree(JTree tree) {
        tree.setBackground(ColorUtils.getColorBackground());
        tree.setForeground(ColorUtils.getColorForeground());
        tree.setFont(FontUtils.getFont(18));

        tree.setCellRenderer(
                new DefaultTreeCellRenderer() {
                    @Override
                    public void setBackgroundNonSelectionColor(Color newColor) {
                        super.setBackgroundNonSelectionColor(ColorUtils.getColorBackground());
                    }

                    @Override
                    public void setTextNonSelectionColor(Color newColor) {
                        super.setTextNonSelectionColor(ColorUtils.getColorForeground());
                    }
                });
    }


    private void SetColorsAndFonts() {

        JUtils.SetDefaultPanelColors(panelMain);
        JUtils.SetDefaultTextAreaColors(textAreaSkills);
        JUtils.SetDefaultTextAreaFont(textAreaSkills, 18);
    }


    @Override
    public String getTitle() {
        return "CardSkills";
    }

    @Override
    public JPanel getPanel() {
        return panelMain;
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(5, 4, new Insets(10, 10, 10, 10), -1, -1));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(1, 0, 4, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(treeSkillList);
        final JScrollPane scrollPane2 = new JScrollPane();
        panelMain.add(scrollPane2, new GridConstraints(1, 3, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        treeSelectedSkills = new JTree();
        scrollPane2.setViewportView(treeSelectedSkills);
        addSelectedSkillButton = new JButton();
        addSelectedSkillButton.setText("-> Add Selected Skill -> ->");
        panelMain.add(addSelectedSkillButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createCustomSkillButton = new JButton();
        createCustomSkillButton.setEnabled(false);
        createCustomSkillButton.setText("<- Create Custom Skill <-");
        panelMain.add(createCustomSkillButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearButton = new JButton();
        clearButton.setText("Clear");
        panelMain.add(clearButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textAreaSkills = new JTextArea();
        textAreaSkills.setLineWrap(false);
        textAreaSkills.setText("Skills");
        textAreaSkills.setWrapStyleWord(false);
        panelMain.add(textAreaSkills, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        treeSkillList = new JTree();
    }
}
