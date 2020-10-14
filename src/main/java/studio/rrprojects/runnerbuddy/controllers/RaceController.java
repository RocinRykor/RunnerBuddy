package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.race.RacePriorityContainer;

import javax.swing.*;
import java.util.ArrayList;

public class RaceController {
   ArrayList<RacePriorityContainer> racePriorityTable;
    private String selectedRace;

    public RaceController(CharacterContainer characterContainer) {

        LoadTables();

        //Sets The Default
        SetRace("Human");
    }

    private void LoadTables() {
        racePriorityTable = new ArrayList<>();
        racePriorityTable.add(new RacePriorityContainer("C", new String[]{"Troll", "Elf"}));
        racePriorityTable.add(new RacePriorityContainer("D", new String[]{"Dwarf", "Ork"}));
        racePriorityTable.add(new RacePriorityContainer("E", new String[]{"Human"}));
    }

    public ComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        box.addElement("-- Select Race Level --");
        for (RacePriorityContainer race: racePriorityTable) {
            box.addElement(String.format("%s - %s", race.getPriorityLevel(), race.GetRaceListAsString()));
        }
        return box;
    }

    public RacePriorityContainer SearchSubMenu(String input) {
        String searchTerm = String.valueOf(input.charAt(0));
        for (RacePriorityContainer race: racePriorityTable) {
            if (race.getPriorityLevel().equalsIgnoreCase(searchTerm)) {
                return race;
            }
        }
        return null;
    }

    public void setSelectedRace(Object selectedItem) {
        if (selectedItem.toString().startsWith("--")) {
            SetRace("Human");
            return;
        }

        SetRace(selectedItem.toString());
    }

    private void SetRace(String race) {
        selectedRace = race;
    }

    public String getSelectedRace() {
        return selectedRace;
    }

}
