package studio.rrprojects.runnerbuddy.gui.popups.components;

import studio.rrprojects.runnerbuddy.misc.PriorityOptions;

import javax.swing.*;
import java.awt.*;

public class PriorityModule extends JPanel {
    private final String priorityLevel;
    private final PriorityOptions priorityOptions;
    private JLabel displayLabel;
    private JComboBox<String> boxPrioritySelection;
    private JComboBox<String> boxSubSelection;

    public PriorityModule(String priorityLevel, PriorityOptions priorityOptions) {
        this.priorityLevel = priorityLevel;
        this.priorityOptions = priorityOptions;

        setLayout(new FlowLayout());

        Initialize();

        setVisible(true);
    }

    private void Initialize() {
        //Dimension preferred = new Dimension(100, 100);

        displayLabel = new JLabel("Priority Level: " + priorityLevel.toUpperCase());
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        //displayLabel.setPreferredSize(preferred);
        
        boxPrioritySelection = new JComboBox<>();
        boxPrioritySelection.setModel(PopulateComboBox());
        //boxPrioritySelection.setPreferredSize(preferred);

        boxSubSelection = new JComboBox<>();
        boxSubSelection.setModel(PopulateComboBox());
        //boxSubSelection.setPreferredSize(preferred);

        add(displayLabel);
        add(boxPrioritySelection);
        add(boxSubSelection);
        boxSubSelection.setVisible(true);
    }

    private ComboBoxModel<String> PopulateComboBox() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement(" == SELECT A PRIORITY OPTION ==");

        if (priorityOptions.containsCategory("Race")) {
            model.addElement(priorityOptions.get("Race").getDisplayString());
        }

        model.addElement(priorityOptions.get("Magic").getDisplayString());
        model.addElement(priorityOptions.get("Attributes").getDisplayString());
        model.addElement(priorityOptions.get("Skills").getDisplayString());
        model.addElement(priorityOptions.get("Resources").getDisplayString());

        return model;
    }
}
