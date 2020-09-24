package studio.rrprojects.runnerbuddy.gui.cards;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.containers.skills.SkillContainer;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.popups.EditSkillPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SkillCard {
    private CharacterContainer character;
    private JList<String> listAvailable;
    private JList<String> listSelected;
    private JPanel panelMain;
    private JComboBox<String> boxSkillType;
    private JButton buttonClear;
    private JLabel labelActive;
    private JLabel labelKnowledge;
    private JLabel labelLanguage;
    private LinkedHashMap<String, DefaultListModel<String>> tableSkillType;
    private LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> tableSkillTypeList;
    private LinkedHashMap<String, SkillContainer> tableActiveSkills, tableKnowledgeSkills, tableLanguageSkills;

    public SkillCard(CharacterContainer characterContainer) {

        //TODO Move File Loading

        character = characterContainer;
        PopulateAllLists();
        UpdateAll();
        listAvailable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(e.getPoint());
                    SkillSelect(list.getSelectedValue().toString());
                }
            }
        });
        listSelected.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JList list = (JList) e.getSource();
                if (e.getClickCount() == 2) {

                    // Double-click detected
                    int index = list.locationToIndex(e.getPoint());
                    EditSkill(list.getSelectedValue().toString());
                }
            }
        });
        boxSkillType.addActionListener(actionEvent -> UpdateAvailableList());
        buttonClear.addActionListener(actionEvent -> {
            ClearAll();
            UpdateAll();
        });
    }



    private void ClearAll() {
        LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> skillsLists = character.getSkillsController().getSelectedSkillsLists();
        for (Map.Entry<String, LinkedHashMap<String, SkillContainer>> map : skillsLists.entrySet()) {
            map.getValue().clear();
        }
    }

    private void UpdateAvailableList() {
        String searchTerm = Objects.requireNonNull(boxSkillType.getSelectedItem()).toString();
        listAvailable.setModel(tableSkillType.get(searchTerm));
    }

    public void UpdateAll() {
        UpdateSelectedLists();
        UpdateLabels();
        panelMain.repaint();
    }

    private void UpdateLabels() {
        int intelligence = character.getAttributeController().getSelectedAttributes().getAttribute("Intelligence");

        //Active Skills
        UpdateLabel("Active Skills", labelActive, character.getSkillsController().getBaseSkillPoints());
        //Knowledge Skills
        UpdateLabel("Knowledge Skills", labelKnowledge, intelligence * 5);
        //Language Skills
        UpdateLabel("Language Skills", labelLanguage, (int) Math.floor(intelligence * 1.5));
    }

    private void UpdateLabel(String title, JLabel label, int maxSkillPoints) {
        int totalPoints = 0;
        LinkedHashMap<String, SkillContainer> table = character.getSkillsController().getSelectedSkillsLists().get(title);
        for (Map.Entry<String, SkillContainer> skill : table.entrySet()) {
            totalPoints += skill.getValue().getTotalCost();
        }
        System.out.println("After: " + totalPoints);
        label.setText(String.format("%s: (%d/%d)", title, totalPoints, maxSkillPoints));
        if (totalPoints > maxSkillPoints) {
            label.setForeground(new Color(0xFFFF0000, true));
        } else {
            label.setForeground(new Color(-11805347));
        }
    }

    private void UpdateSelectedLists() {
        LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> skillsLists = character.getSkillsController().getSelectedSkillsLists();
        DefaultListModel<String> list = new DefaultListModel<String>();
        for (Map.Entry<String, LinkedHashMap<String, SkillContainer>> map : skillsLists.entrySet()) {
            list.addElement("-- " + map.getKey() + " --");
            for (Map.Entry<String, SkillContainer> skill : map.getValue().entrySet()) {
                list.addElement(skill.getKey());
            }
        }

        listSelected.setModel(list);
    }

    private void PopulateAllLists() {
        //Init Skill Type Selector
        DefaultComboBoxModel<String> modelSkillType = new DefaultComboBoxModel<>();
        tableSkillType = new LinkedHashMap<>();
        tableSkillTypeList = new LinkedHashMap<>();

        //Active Skills
        tableActiveSkills = new LinkedHashMap<>();
        DefaultListModel<String> modelActiveSkills = LoadSkills("Active Skills", "SR3E_active_skills.json", new String[]{"Body", "Strength", "Quickness", "Intelligence", "Charisma", "Willpower", "Reaction"}, tableActiveSkills);
        tableSkillType.put("Active Skills", modelActiveSkills);
        tableSkillTypeList.put("Active Skills", tableActiveSkills);

        //Knowledge Skills
        tableKnowledgeSkills = new LinkedHashMap<>();
        DefaultListModel<String> modelKnowledgeSkills = LoadSkills("Knowledge Skills", "SR3E_knowledge_skills.json", new String[]{"Street", "Academic", "Sixth World", "Interests"}, tableKnowledgeSkills);
        tableSkillType.put("Knowledge Skills", modelKnowledgeSkills);
        tableSkillTypeList.put("Knowledge Skills", tableKnowledgeSkills);

        //Language Skills
        tableLanguageSkills = new LinkedHashMap<>();
        DefaultListModel<String> modelLanguageSkills = LoadSkills("Language Skills", "SR3E_language_skills.json", new String[]{"Languages"}, tableLanguageSkills);
        tableSkillType.put("Language Skills", modelLanguageSkills);
        tableSkillTypeList.put("Language Skills", tableLanguageSkills);

        //Populate Skill Type Selector
        for (Map.Entry<String, DefaultListModel<String>> type : tableSkillType.entrySet()) {
            modelSkillType.addElement(type.getKey());
        }

        boxSkillType.setModel(modelSkillType);
        listAvailable.setModel(modelActiveSkills);
    }

    private DefaultListModel<String> LoadSkills(String skillType, String fileName, String[] separators, LinkedHashMap<String, SkillContainer> hashMap) {
        DefaultListModel<String> list = new DefaultListModel<>();
        //Load The File and Create ArrayList
        ArrayList<SkillContainer> tmpSkillList = CreateArrayFromJSON(fileName, skillType);
        //Split Arraylist into separate hashmap for each linked attribute and add Each Hashmap to a larger group
        assert tmpSkillList != null;
        LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> tableSkills = SplitArray(tmpSkillList, separators);
        //Create Box from Main Group
        for (Map.Entry<String, LinkedHashMap<String, SkillContainer>> group : tableSkills.entrySet()) {
            list.addElement("-- " + group.getKey() + " --");
            for (Map.Entry<String, SkillContainer> skill : group.getValue().entrySet()) {
                list.addElement(skill.getKey());
                hashMap.put(skill.getKey(), skill.getValue());
            }
        }

        return list;
    }

    private LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> SplitArray(ArrayList<SkillContainer> tmpSkillList, String[] strings) {
        LinkedHashMap<String, LinkedHashMap<String, SkillContainer>> arrayList = new LinkedHashMap<>();
        for (String category : strings) {
            arrayList.put(category, new LinkedHashMap<>());
        }

        for (SkillContainer skill : tmpSkillList) {
            if (arrayList.containsValue(arrayList.get(skill.getAttribute()))) {
                arrayList.get(skill.getAttribute()).put(skill.getSkillName(), skill);
            } else {
                arrayList.get(skill.getCategory()).put(skill.getSkillName(), skill);
            }
        }

        return arrayList;
    }

    private ArrayList<SkillContainer> CreateArrayFromJSON(String fileName, String skillType) {
        ArrayList<SkillContainer> arrayList = new ArrayList<>();
        String file = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "RunnerBuddy" + File.separator + "JSON" + File.separator + fileName;


        JsonObject mainObj;
        try {
            FileReader reader = new FileReader(new File(file));
            mainObj = (JsonObject) Json.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        for (JsonObject.Member skill : mainObj) {
            arrayList.add(new SkillContainer(skill, skillType));
        }

        return arrayList;
    }

    private void SkillSelect(String skill) {
        SkillContainer baseSkill = null;
        String searchTerm = Objects.requireNonNull(boxSkillType.getSelectedItem()).toString();

        baseSkill = tableSkillTypeList.get(searchTerm).get(skill);

        if (baseSkill != null) {
            new EditSkillPopup(baseSkill, character, this);
        }
    }

    private void EditSkill(String skill) {
        SkillContainer baseSkill = null;
        String searchTerm = Objects.requireNonNull(boxSkillType.getSelectedItem()).toString();

        baseSkill = character.getSkillsController().getSelectedSkillsLists().get(searchTerm).get(skill);

        if (baseSkill != null) {
            new EditSkillPopup(baseSkill, character, this);
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
        panelMain.setLayout(new GridLayoutManager(8, 10, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setBackground(new Color(-14079180));
        panelMain.setForeground(new Color(-11805347));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(1, 0, 7, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(157, 128), null, 0, false));
        listAvailable = new JList();
        listAvailable.setBackground(new Color(-14079180));
        listAvailable.setForeground(new Color(-11805347));
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        listAvailable.setModel(defaultListModel1);
        scrollPane1.setViewportView(listAvailable);
        listSelected = new JList();
        listSelected.setBackground(new Color(-14079180));
        listSelected.setForeground(new Color(-11805347));
        listSelected.setSelectionBackground(new Color(-14079180));
        panelMain.add(listSelected, new GridConstraints(1, 7, 7, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JSeparator separator1 = new JSeparator();
        separator1.setOrientation(1);
        panelMain.add(separator1, new GridConstraints(0, 6, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        boxSkillType = new JComboBox();
        boxSkillType.setBackground(new Color(-14079180));
        boxSkillType.setForeground(new Color(-11805347));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        boxSkillType.setModel(defaultComboBoxModel1);
        panelMain.add(boxSkillType, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JSeparator separator2 = new JSeparator();
        separator2.setOrientation(1);
        panelMain.add(separator2, new GridConstraints(0, 2, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        labelActive = new JLabel();
        labelActive.setBackground(new Color(-14079180));
        labelActive.setForeground(new Color(-11805347));
        labelActive.setText("Active Skills: X/Y");
        panelMain.add(labelActive, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panelMain.add(spacer4, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panelMain.add(spacer5, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panelMain.add(spacer6, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelKnowledge = new JLabel();
        labelKnowledge.setBackground(new Color(-14079180));
        labelKnowledge.setForeground(new Color(-11805347));
        labelKnowledge.setText("Knowledge Skills: X/Y");
        panelMain.add(labelKnowledge, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelLanguage = new JLabel();
        labelLanguage.setBackground(new Color(-14079180));
        labelLanguage.setForeground(new Color(-11805347));
        labelLanguage.setText("Language Skills: X/Y");
        panelMain.add(labelLanguage, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonClear = new JButton();
        buttonClear.setBackground(new Color(-14079180));
        buttonClear.setForeground(new Color(-11805347));
        buttonClear.setText("CLEAR ALL");
        panelMain.add(buttonClear, new GridConstraints(0, 7, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
