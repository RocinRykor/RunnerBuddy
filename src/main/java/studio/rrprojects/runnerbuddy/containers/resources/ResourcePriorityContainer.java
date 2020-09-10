package studio.rrprojects.runnerbuddy.containers.resources;

import java.text.NumberFormat;

public class ResourcePriorityContainer {
    String priorityLevel;
    int nuyenAmount;

    public ResourcePriorityContainer(String priorityLevel, int nuyenAmount) {
        this.priorityLevel = priorityLevel;
        this.nuyenAmount = nuyenAmount;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public int getNuyenAmount() {
        return nuyenAmount;
    }

    public String getNuyenAmountString() {
        return NumberFormat.getIntegerInstance().format(nuyenAmount);
    }
}
