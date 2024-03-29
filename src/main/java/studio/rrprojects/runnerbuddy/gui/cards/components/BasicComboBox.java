package studio.rrprojects.runnerbuddy.gui.cards.components;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import studio.rrprojects.runnerbuddy.gui.cards.magic.MagicalInfoCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BasicComboBox extends JPanel {

    private JComboBox comboBox;
    private JLabel labelTitle;
    private JPanel panelMain;

    public BasicComboBox(String title) {
        labelTitle.setText(title);
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
        panelMain.setLayout(new GridLayoutManager(1, 2, new Insets(10, 10, 10, 10), -1, -1));
        labelTitle = new JLabel();
        labelTitle.setText("Label");
        panelMain.add(labelTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox = new JComboBox();
        panelMain.add(comboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    public Component getPanel() {
        return panelMain;
    }

    public void addOption(Object object) {
        comboBox.addItem(object);
    }

    public void addEvenet(MagicalInfoCard magicalInfoCard) {
        comboBox.addActionListener(actionEvent -> {
            magicalInfoCard.Update();
        });
    }

    public Object getSelectedItem() {
        return comboBox.getSelectedItem();
    }

    public void setOptions(Object[] objectArray) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(Arrays.asList(objectArray));
        comboBox.setModel(model);
    }

    public void setOptions(ArrayList<String> stringArray) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addAll(Arrays.asList(stringArray));
        comboBox.setModel(model);
    }

    public void clearOptions() {
        comboBox.removeAllItems();
    }

    public void setBoxEnabled(boolean b) {
        comboBox.setEnabled(b);
    }
}
