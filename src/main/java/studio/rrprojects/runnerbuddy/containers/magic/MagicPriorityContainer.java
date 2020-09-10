package studio.rrprojects.runnerbuddy.containers.magic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MagicPriorityContainer {
    String priorityLevel;
    ArrayList<String> magicList;

    public MagicPriorityContainer(String priorityLevel, String[] magicLevel) {
        this.priorityLevel = priorityLevel;

        magicList = new ArrayList<>();
        magicList.addAll(Arrays.asList(magicLevel));
    }

    public String GetListAsString() {
        if (magicList.size() > 1) {
            StringBuilder string = new StringBuilder(magicList.get(0));
            for (int i = 1; i < magicList.size(); i++) {
                string.append(" / ").append(magicList.get(i));
            }
            return string.toString();
        } else {
            return magicList.get(0);
        }
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public ArrayList<String> getMagicList() {
        return magicList;
    }

    public ComboBoxModel<String> GetPriorityBox() {
        DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
        for (String magic: magicList) {
            box.addElement(magic);
        }
        return box;
    }
}
