package studio.rrprojects.runnerbuddy.gui.popups;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.constants.PriorityConstants;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityLevelGroup;
import studio.rrprojects.runnerbuddy.gui.CharacterCreationWindow;
import studio.rrprojects.runnerbuddy.gui.LaunchWindow;
import studio.rrprojects.runnerbuddy.gui.cards.components.PriorityModule;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class NewCharacterPriorityPopup extends JFrame {
    private final String title = "Select Character Priority";

    private final Boolean forceMagic = false;

    private JPanel panelMain;
    private JLabel labelHeader;
    private JPanel panelPrimary;
    private JButton submitButton;
    private JButton buttonCancel;

    private PriorityModule priorityModuleA;
    private PriorityModule priorityModuleB;
    private PriorityModule priorityModuleC;
    private PriorityModule priorityModuleD;
    private PriorityModule priorityModuleE;
    private JPanel panelModules;
    private JButton randomButton;
    private final CharacterContainer characterContainer;

    private final LinkedHashMap<String, PriorityModule> priorityModuleMap = new LinkedHashMap<>();

    private final String confirmationString = "Are you sure?\n" +
            "\n" +
            "Character priorities can not be changed after this point.";
    private final String confirmationTitleString = "Confirm Priority Choice...";

    private final String panelTitle = "Priority Selection";

    private ArrayList<String> availiableOptions;

    public NewCharacterPriorityPopup(CharacterContainer characterContainer) {
        setTitle(title);
        this.characterContainer = characterContainer;

        setContentPane(panelMain);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setSize(600, 400);
        setVisible(true);

        InitModules();
        submitButton.addActionListener(actionEvent -> SubmitEvent());
        buttonCancel.addActionListener(actionEvent -> CancelEvent());
        randomButton.addActionListener(actionEvent -> RandomEvent());
    }

    private void RandomEvent() {
        //Clear the selected priorities list
        characterContainer.getPriorityController().getTakenPriorities().clear();
        for (Map.Entry<String, PriorityModule> entry : priorityModuleMap.entrySet()) {
            PriorityModule priorityModule = entry.getValue();

            priorityModule.ClearSelection();
        }

        availiableOptions = new ArrayList<>();
        availiableOptions.addAll(Arrays.asList("A", "B", "C", "D", "E"));

        if (forceMagic) {
            RadomRace();
            RandomMagic();
            RandomChoice(PriorityConstants.RESOURCES);
            RandomChoice(PriorityConstants.ATTRIBUTES);
            RandomChoice(PriorityConstants.SKILLS);

        } else {
            RadomRace();
            RandomChoice(PriorityConstants.RESOURCES);
            RandomChoice(PriorityConstants.ATTRIBUTES);
            RandomChoice(PriorityConstants.SKILLS);
            RandomChoice(PriorityConstants.MAGIC);
        }

    }

    private void RandomMagic() {
        ArrayList<String> choices = new ArrayList<>(Arrays.asList("A", "B"));

        Collections.shuffle(choices);

        String selectedKey = choices.get(0);

        priorityModuleMap.get(selectedKey).getPriorityLevelGroup().setSelectionByCategory(PriorityConstants.MAGIC);

        availiableOptions.remove(selectedKey);
    }

    private void RandomChoice(String category) {

        ArrayList<String> choices = availiableOptions;

        Collections.shuffle(choices);

        String selectedKey = choices.get(0);

        priorityModuleMap.get(selectedKey).getPriorityLevelGroup().setSelectionByCategory(category);

        availiableOptions.remove(selectedKey);
    }

    private void RadomRace() {
        ArrayList<String> choices = new ArrayList<>(Arrays.asList("C", "D", "E"));

        Collections.shuffle(choices);

        String selectedKey = choices.get(0);

        priorityModuleMap.get(selectedKey).getPriorityLevelGroup().setSelectionByCategory(PriorityConstants.RACE);

        availiableOptions.remove(selectedKey);
    }

    private void SubmitEvent() {
        ArrayList<PriorityContainer> priorityList = characterContainer.getPriorityController().getTakenPriorities();
        if (priorityList.size() != 5) {
            String title = "INVALID SELCTION";
            String errorString = "Please ensure all priority levels are selected and try again!";
            JOptionPane.showConfirmDialog(null, errorString, title,
                    JOptionPane.OK_OPTION);
            return;
        }

        if (ConfirmChoice() == 0) {
            Finalize();
        }
    }

    private void Finalize() {
        characterContainer.ProcessPriorities();

        new CharacterCreationWindow(characterContainer);

        this.dispose();
    }

    private int ConfirmChoice() {
        int input = JOptionPane.showConfirmDialog(null, confirmationString, confirmationTitleString,
                JOptionPane.OK_CANCEL_OPTION);

        System.out.println("NewCharacterPriority: " + input);

        return input;
    }

    private void CancelEvent() {
        new LaunchWindow();
        this.dispose();
    }

    private void InitModules() {
        FormatModule(priorityModuleA, "A");
        FormatModule(priorityModuleB, "B");
        FormatModule(priorityModuleC, "C");
        FormatModule(priorityModuleD, "D");
        FormatModule(priorityModuleE, "E");
    }

    private void FormatModule(PriorityModule priorityModule, String priorityKey) {
        priorityModuleMap.put(priorityKey, priorityModule);

        PriorityLevelGroup priorityGroup = characterContainer.getPriorityController().getOptionsByLevel(priorityKey);
        priorityModule.process(priorityGroup);
        priorityModule.setParent(this);
        panelModules.setBorder(BorderFactory.createTitledBorder(panelTitle));
    }

    public CharacterContainer getCharacterContainer() {
        return characterContainer;
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
        panelPrimary.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panelPrimary, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        submitButton = new JButton();
        submitButton.setText("Submit");
        panelPrimary.add(submitButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panelModules = new JPanel();
        panelModules.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelPrimary.add(panelModules, new GridConstraints(0, 0, 5, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        priorityModuleA = new PriorityModule();
        panelModules.add(priorityModuleA.$$$getRootComponent$$$(), new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        priorityModuleB = new PriorityModule();
        panelModules.add(priorityModuleB.$$$getRootComponent$$$(), new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        priorityModuleC = new PriorityModule();
        panelModules.add(priorityModuleC.$$$getRootComponent$$$(), new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        priorityModuleD = new PriorityModule();
        panelModules.add(priorityModuleD.$$$getRootComponent$$$(), new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        priorityModuleE = new PriorityModule();
        panelModules.add(priorityModuleE.$$$getRootComponent$$$(), new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        randomButton = new JButton();
        randomButton.setText("Random");
        panelPrimary.add(randomButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panelPrimary.add(buttonCancel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Click a priority level to change it!");
        panelMain.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    public void RandomizeCharacter() {
        RandomEvent();
        Finalize();
    }
}
