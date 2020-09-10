package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.magic.MagicPriorityContainer;
import studio.rrprojects.runnerbuddy.containers.race.RacePriorityContainer;

import javax.swing.*;
import java.util.ArrayList;

public class MagicController {
    ArrayList<MagicPriorityContainer> magicPriorityTable;

    public MagicController(){
        LoadTables();
    }

    private void LoadTables() {
        magicPriorityTable = new ArrayList<MagicPriorityContainer>();
        magicPriorityTable.add(new MagicPriorityContainer("A", new String[]{"Full Magician"}));
        magicPriorityTable.add(new MagicPriorityContainer("B", new String[]{"Adept", "Aspected Magician"}));
        magicPriorityTable.add(new MagicPriorityContainer("C", new String[]{"Mundane"}));
        magicPriorityTable.add(new MagicPriorityContainer("D", new String[]{"Mundane"}));
        magicPriorityTable.add(new MagicPriorityContainer("E", new String[]{"Mundane"}));
    }

    public ComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        box.addElement("-- Select Magic Level --");
        for (MagicPriorityContainer magic: magicPriorityTable) {
            box.addElement(String.format("%s - %s", magic.getPriorityLevel(), magic.GetListAsString()));
        }
        return box;
    }

    public MagicPriorityContainer SearchSubMenu(String input) {
        String searchTerm = String.valueOf(input.charAt(0));
        for (MagicPriorityContainer magic: magicPriorityTable) {
            if (magic.getPriorityLevel().equalsIgnoreCase(searchTerm)) {
                return magic;
            }
        }
        return null;
    }
}
