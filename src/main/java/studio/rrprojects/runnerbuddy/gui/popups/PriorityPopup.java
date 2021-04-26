package studio.rrprojects.runnerbuddy.gui.popups;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.gui.cards.Priority;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;
import studio.rrprojects.runnerbuddy.utils.JUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PriorityPopup {
    private final Priority priority;
    private final CharacterContainer characterContainer;
    private final String keyword;
    private final JFrame frame;
    private JPanel panelMain;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton buttonCancel;
    private JButton buttonClear;
    private ArrayList<ButtonPriorityGroup> listButtons;
    private final int buttonSize = 18;
    private PriorityGroup priorityGroup;

    public PriorityPopup(Priority priority, CharacterContainer characterContainer, String keyword) {
        this.priority = priority;
        this.characterContainer = characterContainer;
        this.keyword = keyword;

        frame = new JFrame();
        JUtils.OpenFrameAtMouseLocation(frame);
        frame.setContentPane(panelMain);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

        CreateListOfOptionButtons();
        Formatting();
        GrabPriorityOptions();
    }

    private void GrabPriorityOptions() {
        //priorityGroup = characterContainer.getPriorityController().getPriorityGroupByKeyword(keyword);

        //Each button is loaded with just a number 1-5, this converts each one to a readable string
        for (ButtonPriorityGroup buttonGroup : listButtons) {
            PriorityGroup.PriorityOption priorityOption = priorityGroup.getOptionByNumber(buttonGroup.button.getText());
            buttonGroup.addOption(priorityOption);
        }
    }

    private void ChoosePriority(PriorityGroup.PriorityOption priorityOption) {
        priorityGroup.setSelectedOption(priorityOption);
        //priority.UpdateButtons();
        priority.SubmitEvent(keyword, priorityOption);
        frame.dispose();
    }

    private void CreateListOfOptionButtons() {
        listButtons = new ArrayList<>();
        listButtons.add(new ButtonPriorityGroup(button1));
        listButtons.add(new ButtonPriorityGroup(button2));
        listButtons.add(new ButtonPriorityGroup(button3));
        listButtons.add(new ButtonPriorityGroup(button4));
        listButtons.add(new ButtonPriorityGroup(button5));
    }

    private void Formatting() {
        JUtils.SetDefaultPanelColors(panelMain);

        //Format the list of buttons
        for (ButtonPriorityGroup group : listButtons) {
            JUtils.SetDefaultButtonColorsAndFont(group.button, buttonSize);
        }

        //Then the individual ones
        JUtils.SetDefaultButtonColorsAndFont(buttonClear, buttonSize);
        JUtils.SetDefaultButtonColorsAndFont(buttonCancel, buttonSize);

        buttonClear.addActionListener(actionEvent -> ChoosePriority(null));
        buttonCancel.addActionListener(actionEvent -> ClosePopup());
    }

    private void ClosePopup() {
        frame.dispose();
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
        panelMain.setLayout(new GridLayoutManager(6, 5, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.setMinimumSize(new Dimension(480, 640));
        panelMain.setPreferredSize(new Dimension(480, 640));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panelMain.add(buttonCancel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonClear = new JButton();
        buttonClear.setText("Clear");
        panelMain.add(buttonClear, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button5 = new JButton();
        button5.setText("5");
        panelMain.add(button5, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button4 = new JButton();
        button4.setText("4");
        panelMain.add(button4, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button3 = new JButton();
        button3.setText("3");
        panelMain.add(button3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button2 = new JButton();
        button2.setText("2");
        panelMain.add(button2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button1 = new JButton();
        button1.setText("1");
        panelMain.add(button1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private class ButtonPriorityGroup {
        private final JButton button;
        private PriorityGroup.PriorityOption priorityOption;

        public ButtonPriorityGroup(JButton button) {
            this.button = button;
        }

        public void addOption(PriorityGroup.PriorityOption priorityOption) {
            this.priorityOption = priorityOption;
            setText();
            addActionListener();
        }

        public void setText() {
            button.setText("Priority " + priorityOption.getPriorityLevel() + ": " + priorityOption.getDisplayName());
        }

        public void addActionListener() {
            button.addActionListener(actionEvent -> ChoosePriority(priorityOption));
        }

    }
}
