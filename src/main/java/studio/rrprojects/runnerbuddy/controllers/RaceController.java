package studio.rrprojects.runnerbuddy.controllers;

import studio.rrprojects.runnerbuddy.containers.character.CharacterContainer;
import studio.rrprojects.runnerbuddy.containers.race.RacePriorityContainer;
import studio.rrprojects.runnerbuddy.misc.PriorityGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class RaceController {
    private final CharacterContainer characterContainer;
    ArrayList<RacePriorityContainer> racePriorityTable;
    private RaceContainer selectedRace;
    private PriorityGroup priorityGroup;
    private ArrayList<RaceContainer> listRaceContainer;
    private PriorityGroup.PriorityOption selectedPriorityOption;

    public RaceController(CharacterContainer characterContainer) {
        this.characterContainer = characterContainer;

        GeneratePriorityGroup();
        GenerateListRaceContainer();
        SetDefaultRace();
    }

    private void SetDefaultRace() {
        selectedRace = getRace("Human");
    }

    private RaceContainer getRace(String searchTerm) {

        for (RaceContainer race : listRaceContainer) {
            if (race.raceName.equalsIgnoreCase(searchTerm)) {
                return race;
            }
        }
        return null;
    }

    private void GenerateListRaceContainer() {
        listRaceContainer = new ArrayList<>();
        listRaceContainer.add(new RaceContainer("Human", 0, 0, 0, 0, 0 ,0));
        listRaceContainer.add(new RaceContainer("Dwarf", 1, 0, 2, 0, 0 ,1));
        listRaceContainer.add(new RaceContainer("Elf", 0, 1, 0, 2, 0 ,0));
        listRaceContainer.add(new RaceContainer("Ork", 3, 0, 2, -1, -1,0));
        listRaceContainer.add(new RaceContainer("Troll", 5, -1, 4, -2, -2 ,0));
    }

    private void GeneratePriorityGroup() {
        priorityGroup = new PriorityGroup("Race");
        priorityGroup.addOption(1, "C", "Troll");
        priorityGroup.addOption(2, "C", "Elf");
        priorityGroup.addOption(3, "D", "Dwarf");
        priorityGroup.addOption(4, "D", "OrK");
        priorityGroup.addOption(5, "E", "Human");
    }


    public PriorityGroup getPriorityGroup() {
        return priorityGroup;
    }

    public RaceContainer getSelectedRace() {
        return selectedRace;
    }

    public void setSelectedRace(String raceName) {
        selectedRace = getRace(raceName);
    }

    public void setSelectedPriorityOption(PriorityGroup.PriorityOption priorityOption) {
        selectedPriorityOption = priorityOption;
        if (priorityOption == null) {
            SetDefaultRace();
        } else {
            setSelectedRace(priorityOption.getDisplayName());
        }
        characterContainer.getAttributeController().getAttributesCard().MassUpdateEvent();
    }

    public class RaceContainer {
        private final String raceName;
        private HashMap<String, Integer> modAttributeMap;

        public RaceContainer(String raceName, int modBody, int modQuickness, int modStrength, int modCharisma, int modIntelligence, int modWillpower) {
            this.raceName = raceName;

            modAttributeMap = new HashMap<>();
            modAttributeMap.put("Body", modBody);
            modAttributeMap.put("Quickness", modQuickness);
            modAttributeMap.put("Strength", modStrength);
            modAttributeMap.put("Charisma", modCharisma);
            modAttributeMap.put("Intelligence", modIntelligence);
            modAttributeMap.put("Willpower", modWillpower);
            modAttributeMap.put("Essence", 0);
            modAttributeMap.put("Magic", 0);
            modAttributeMap.put("Reaction", 0);
        }

        public String getRaceName() {
            return raceName;
        }

        public HashMap<String, Integer> getModAttributeMap() {
            return modAttributeMap;
        }
    }
}
