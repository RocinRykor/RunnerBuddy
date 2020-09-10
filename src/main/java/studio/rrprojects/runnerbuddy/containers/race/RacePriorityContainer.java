package studio.rrprojects.runnerbuddy.containers.race;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RacePriorityContainer {
    String priorityLevel;
    ArrayList<String> raceList;


    public RacePriorityContainer(String priorityLevel, String[] races) {
        this.priorityLevel = priorityLevel;

        raceList = new ArrayList<>();
        raceList.addAll(Arrays.asList(races));
    }

    public String GetRaceListAsString() {
        if (raceList.size() > 1) {
            StringBuilder string = new StringBuilder(raceList.get(0));
            for (int i = 1; i < raceList.size(); i++) {
                string.append(" / ").append(raceList.get(i));
            }
            return string.toString();
        } else {
            return raceList.get(0);
        }
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public ArrayList<String> getRaceList() {
        return raceList;
    }

    public DefaultComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        for (String race: raceList) {
            box.addElement(race);
        }
        return box;
    }
}
