package studio.rrprojects.runnerbuddy.gui.cards;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.json.JSONObject;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.controllers.RunnerBuilderController;
import studio.rrprojects.runnerbuddy.utils.ColorUtils;
import studio.rrprojects.runnerbuddy.utils.FontUtils;
import studio.rrprojects.runnerbuddy.utils.JUtils;
import studio.rrprojects.runnerbuddy.utils.TextUtils;
import studio.rrprojects.util_library.FileUtil;
import studio.rrprojects.util_library.JSONUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Skills extends Card {
    private JPanel panelMain;
    private JTree treeSkillList;

    public Skills(RunnerBuilderController controller, CharacterContainer characterContainer) {
        this.controller = controller;
        this.characterContainer = characterContainer;


        $$$setupUI$$$();
        SetColorsAndFonts();
        FormatTree(treeSkillList);
        PopulateSkillList();
    }

    private void PopulateSkillList() {
        DefaultTreeModel model = (DefaultTreeModel) treeSkillList.getModel();
        model.setRoot(new DefaultMutableTreeNode("Skills"));
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

        root.add(loadJSONAndConvert("SR3E_active_skills.json", "Active Skills"));
        root.add(loadJSONAndConvert("SR3E_knowledge_skills.json", "Knowledge Skills"));
        root.add(loadJSONAndConvert("SR3E_language_skills.json", "Language Skills"));

        model.reload(root);
    }

    private MutableTreeNode loadJSONAndConvert(String jsonName, String nodeName) {
        JSONObject obj = JSONUtil.loadJsonFromFile(FileUtil.loadFileFromPath("JSON/Skills/" + jsonName));
        Map<String, JSONObject> map = JSONUtil.JsonToMap(obj);
        return convertMapToTreeNode(map, nodeName);
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
        panelMain.setLayout(new GridLayoutManager(1, 2, new Insets(10, 10, 10, 10), -1, -1));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(treeSkillList);
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
