package studio.rrprojects.runnerbuddy.gui.cards.components;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityContainer;
import studio.rrprojects.runnerbuddy.containers.priority.PriorityLevelGroup;
import studio.rrprojects.runnerbuddy.gui.popups.NewCharacterPriorityPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PriorityModule extends JComponent {
    private JPanel panelMain;
    private JLabel labelPriority;
    private JLabel labelSelection;

    private String priorityLevel = "Unavailable";
    private final String stringLabelBase = "Priority Level ";

    private PriorityLevelGroup priorityLevelGroup;
    private NewCharacterPriorityPopup parent;

    public PriorityModule() {

        SetPriorityLabel();

        Update();

        panelMain.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GeneratePopUp(e.getX(), e.getY());
            }
        });
    }

    public void Update() {
        updateSelectionLabel();
    }

    private void updateSelectionLabel() {
        PriorityContainer selectedPriorityContainer = null;

        try {
            selectedPriorityContainer = priorityLevelGroup.getSelectedPriority();
        } catch (NullPointerException ignored) { }


        if (selectedPriorityContainer != null) {
            labelSelection.setText(selectedPriorityContainer.toString());
        } else {
            labelSelection.setText("Click to set a value!");
        }

    }

    private void GeneratePopUp(int x, int y) {
        JPopupMenu popup = new JPopupMenu();
        JSeparator separator = new JPopupMenu.Separator();
        JMenuItem clearOption = new JMenuItem("CLEAR");
        JMenuItem cancelOption = new JMenuItem("CANCEL");

        ArrayList<JMenuItem> priortyList = priorityLevelGroup.getPopupMapAsMenuItems();

        for (JMenuItem menuItem : priortyList) {
            popup.add(menuItem);
        }


        popup.add(separator);
        popup.add(clearOption);
        popup.add(cancelOption);

        clearOption.addActionListener(actionEvent -> priorityLevelGroup.ClearSelection());


        popup.show(panelMain, x, y);
    }

    private void SetPriorityLabel() {
        labelPriority.setText(stringLabelBase + priorityLevel + ": ");
    }

    public void setLevelGroup(PriorityLevelGroup priorityLevelGroup) {
        this.priorityLevelGroup = priorityLevelGroup;
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
        panelMain.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        labelPriority = new JLabel();
        labelPriority.setText("Label");
        panelMain.add(labelPriority, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelSelection = new JLabel();
        labelSelection.setText("Label");
        panelMain.add(labelSelection, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    public void process(PriorityLevelGroup priorityGroup) {
        setLevelGroup(priorityGroup);

        priorityGroup.setParent(this);

        priorityLevel = priorityGroup.getPriorityLevel();

        SetPriorityLabel();
    }

    public void setParent(NewCharacterPriorityPopup parent) {
        this.parent = parent;
    }

    public NewCharacterPriorityPopup getParent() {
        return parent;
    }

    public PriorityLevelGroup getPriorityLevelGroup() {
        return priorityLevelGroup;
    }

    public void ClearSelection() {
        priorityLevelGroup.ClearSelection();
        Update();
    }
}
